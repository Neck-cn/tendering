package cn.ych.tendering.service.imp;

import cn.ych.tendering.exception.TenderingEnum;
import cn.ych.tendering.exception.TenderingException;
import cn.ych.tendering.mapper.EnterpriseMapper;
import cn.ych.tendering.pojo.Enterprise;
import cn.ych.tendering.service.EnterpriseService;
import cn.ych.tendering.utils.AESUtil;
import cn.ych.tendering.utils.JwtUtil;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Map;

@Service
@ConfigurationProperties("application.sms")
public class EnterpriseServiceImp implements EnterpriseService {
    @Setter
    private String templateCode;
    @Setter
    private String signName;
    @Setter
    private String accessKeyId;
    @Setter
    private String accessSecret;

    private EnterpriseMapper enterpriseMapper;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private JwtUtil jwtUtil;

    public EnterpriseServiceImp(EnterpriseMapper enterpriseMapper, BCryptPasswordEncoder bCryptPasswordEncoder, JwtUtil jwtUtil) {
        this.enterpriseMapper = enterpriseMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public int register(Enterprise enterprise) {
        try {
            enterprise.setPassword(bCryptPasswordEncoder.encode(enterprise.getPassword()));
            return enterpriseMapper.insert(enterprise);
        } catch (DuplicateKeyException exception) {
            throw new TenderingException(TenderingEnum.USER_IS_EXISTED);
        }
    }

    @Override
    public Map<String, Object> login(Enterprise enterprise) {
        if (System.currentTimeMillis() - enterprise.getTime() > 300000) {
            return null;
        }
        String password = enterprise.getPassword();
        byte[] bytes = Base64.decodeBase64(password);
        String s = DigestUtils.md5DigestAsHex(String.valueOf(enterprise.getTime()).getBytes());
        String decrypt;
        try {
            decrypt = AESUtil.decrypt(new String(bytes), s);
        } catch (Exception e) {
            throw new TenderingException(500, e.getMessage());
        }
        QueryWrapper<Enterprise> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", enterprise.getName());
        enterprise = enterpriseMapper.selectOne(queryWrapper);
        if (enterprise != null && bCryptPasswordEncoder.matches(decrypt, enterprise.getPassword())) {
            Map<String, Object> user = jwtUtil.createJWT(String.valueOf(enterprise.getId()), enterprise.getName(), "user");
            user.put("enterprise", enterprise);
            return user;
        }
        return null;
    }

    @Override
    public int modify(Enterprise enterprise) {
        if (enterprise.getPassword() != null) {
            enterprise.setPassword(bCryptPasswordEncoder.encode(enterprise.getPassword()));
        }
        return enterpriseMapper.updateById(enterprise);
    }

    @Override
    public String sendMsg(String phone) {
        String code = String.valueOf((int) ((Math.random() * 9 + 1) * 100000));
        DefaultProfile profile = DefaultProfile.getProfile("test", accessKeyId, accessSecret);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", signName);
        request.putQueryParameter("TemplateCode", templateCode);
        request.putQueryParameter("TemplateParam", "{\"code\":\"" + code + "\"}");
        try {
            client.getCommonResponse(request);
        } catch (ClientException e) {
            e.printStackTrace();
            return null;
        }
        return code;
    }

    @Override
    public Enterprise getInfo(int id) {
        return enterpriseMapper.selectById(id);
    }

    @Override
    public IPage<Enterprise> selectEnterprise(int pageNo, int pageSize, Enterprise enterprise) {
        IPage<Enterprise> page = new Page<>(pageNo, pageSize);
        QueryWrapper<Enterprise> wrapper = new QueryWrapper<>();
        if (enterprise.getId() != 0) {
            wrapper.eq("id", enterprise.getId());
        }
        if (StringUtils.isNotEmpty(enterprise.getName())) {
            wrapper.eq("name", enterprise.getName());
        }
        if (StringUtils.isNotEmpty(enterprise.getLogo())) {
            wrapper.eq("logo", enterprise.getLogo());
        }
        if (StringUtils.isNotEmpty(enterprise.getLogo())) {
            wrapper.eq("site_url", enterprise.getSite_url());
        }
        if (StringUtils.isNotEmpty(enterprise.getAddress())) {
            wrapper.like("address", "%" + enterprise.getAddress() + "%");
        }
        if (StringUtils.isNotEmpty(enterprise.getPhone())) {
            wrapper.like("phone", "%" + enterprise.getPhone() + "%");
        }
        if (StringUtils.isNotEmpty(enterprise.getE_mail())) {
            wrapper.like("e_mail", "%" + enterprise.getE_mail() + "%");
        }
        wrapper.orderByDesc("id");
        return enterpriseMapper.selectPage(page, wrapper);
    }

}
