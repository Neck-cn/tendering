package cn.ych.tendering;

import cn.ych.tendering.mapper.TenderingMapper;
import cn.ych.tendering.pojo.Admin;
import cn.ych.tendering.pojo.Bid;
import cn.ych.tendering.pojo.Enterprise;
import cn.ych.tendering.pojo.Tendering;
import cn.ych.tendering.service.*;
import cn.ych.tendering.utils.AESUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

import java.util.Date;

@SpringBootTest
class TenderingApplicationTests {

    @Autowired
    private AdminService adminService;
    @Autowired
    private BidService bidService;
    @Autowired
    private EnterpriseService enterpriseService;
    @Autowired
    private ReportService reportService;
    @Autowired
    private TenderingService tenderingService;

    @Test
    void AdminServiceRegisterTest() {
//        Admin admin = new Admin();
//        admin.setUsername("admin");
//        admin.setPassword("123456789");
//        System.out.println(adminService.register(admin));
    }

    @Test
    void AdminServiceLoginTest() throws Exception {
        long time = System.currentTimeMillis();
        Admin admin = new Admin();
        admin.setTime(time);
        admin.setUsername("wangzhuanzhuan");
        String encrypt = AESUtil.encrypt("123456789", DigestUtils.md5DigestAsHex(String.valueOf(admin.getTime()).getBytes()));
        admin.setPassword(Base64.encodeBase64String(encrypt.getBytes()));
        System.out.println(admin);
        System.out.println(adminService.login(admin));
    }

    @Test
    void AdminServiceChangePassTest() {
        Admin admin = new Admin();
        admin.setUsername("admin");
        admin.setPassword("111111");
        System.out.println(adminService.changePassword(admin));
    }

    @Test
    void BidServiceInsertTest() {
        Bid bid = new Bid();
//        for (int i = 0; i < 100; i++) {
//            bid.setContent("测试" + i);
//            bid.setTime(new Date());
//            bid.setName("name" + 1);
//            bid.setE_id(i);
//            bid.setSrc("http://www.baidu.com/" + 1);
//            bid.setStatus(String.valueOf(i % 2));
//            System.out.println(bidService.insert(bid));
//        }
    }

    @Test
    void BidServiceUpdateTest() {
//        Bid bid = new Bid();
//        bid.setId(1);
//        bid.setName("修改。。。。。");
//        System.out.println(bidService.update(bid));
    }

    @Test
    void BidServiceSelectTest() {
        System.out.println(bidService.selectBid(1, 10, ""));
    }

    @Test
    void EnterpriseServiceTest() {
//        enterpriseService.sendMsg("15513267341");
    }

    @Test
    void ReportServiceTest() {
    }

    @Autowired
    private TenderingMapper tenderingMapper;

    @Test
    void TenderingServiceTest() {
//        long time=System.currentTimeMillis();
//        System.out.println(time);
//        System.out.println(new Date(time).getTime());
//        long time=System.currentTimeMillis();
//        System.out.println(time);
//        Tendering tendering=new Tendering();
//        tendering.setStart_time(new Date(1000));
//        tendering.setName("qqqq");
//        tenderingMapper.insert(tendering);
//        QueryWrapper<Tendering> wrapper = new QueryWrapper<>();
//        wrapper.eq("start_time",new Date(1000));
//        System.out.println(tenderingMapper.selectList(wrapper));
//        Enterprise enterprise = new Enterprise();
//        enterprise.setUsername("111");
//        enterpriseService.register(enterprise);
    }

    @Test
    void EnterpriseServiceLoginTest() throws Exception {
        long time = System.currentTimeMillis();
        Enterprise admin = new Enterprise();
        admin.setTime(time);
        admin.setUsername("wangzhuanzhuan");
        String encrypt = AESUtil.encrypt("123456789", DigestUtils.md5DigestAsHex(String.valueOf(admin.getTime()).getBytes()));
        admin.setPassword(Base64.encodeBase64String(encrypt.getBytes()));
        System.out.println(admin);
        System.out.println(enterpriseService.login(admin));
    }

    @Test
    void EnterpriseServiceRegisterTest() throws Exception {
        long time = System.currentTimeMillis();
        Enterprise admin = new Enterprise();
        admin.setTime(time);
        admin.setUsername("wangzhuanzhuan");
        admin.setPassword("123456789");
        System.out.println(admin);
        System.out.println(enterpriseService.register(admin));
    }
}
