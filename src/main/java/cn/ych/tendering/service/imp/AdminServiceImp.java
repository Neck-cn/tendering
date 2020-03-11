package cn.ych.tendering.service.imp;

import cn.ych.tendering.exception.TenderingException;
import cn.ych.tendering.mapper.AdminMapper;
import cn.ych.tendering.pojo.Admin;
import cn.ych.tendering.service.AdminService;
import cn.ych.tendering.utils.AESUtil;
import cn.ych.tendering.utils.JwtUtil;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Map;

@Service
public class AdminServiceImp implements AdminService {
    private AdminMapper adminMapper;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private JwtUtil jwtUtil;

    public AdminServiceImp(AdminMapper adminMapper,BCryptPasswordEncoder bCryptPasswordEncoder,JwtUtil jwtUtil) {
        this.adminMapper = adminMapper;
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
        this.jwtUtil=jwtUtil;
    }

    @Override
    public Map<String, String> login(Admin admin) {
        if (System.currentTimeMillis() - admin.getTime() > 300000){
            return null;
        }
        String password=admin.getPassword();
        byte[] bytes = Base64.decodeBase64(password);
        String s = DigestUtils.md5DigestAsHex(String.valueOf(admin.getTime()).getBytes());
        String decrypt;
        try {
            decrypt = AESUtil.decrypt(new String(bytes), s);
        } catch (Exception e) {
            throw new TenderingException(500, e.getMessage());
        }
        admin = adminMapper.selectById(admin.getUsername());
        if (admin != null && bCryptPasswordEncoder.matches(decrypt, admin.getPassword())) {
            return jwtUtil.createJWT(admin.getUsername(), admin.getUsername(), "admin");
        }
        return null;
    }

    @Override
    public int changePassword(Admin admin) {
        String encode = bCryptPasswordEncoder.encode(admin.getPassword());
        admin.setPassword(encode);
        return adminMapper.updateById(admin);
    }

    @Override
    public int register(Admin admin) {
        String encode = bCryptPasswordEncoder.encode(admin.getPassword());
        admin.setPassword(encode);
        return adminMapper.insert(admin);
    }


}
