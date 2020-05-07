package cn.ych.tendering;

import cn.ych.tendering.mapper.TenderingMapper;
import cn.ych.tendering.pojo.Admin;
import cn.ych.tendering.pojo.Bid;
import cn.ych.tendering.pojo.Enterprise;
import cn.ych.tendering.pojo.Tendering;
import cn.ych.tendering.processor.TenderingProcessor;
import cn.ych.tendering.service.*;
import cn.ych.tendering.utils.AESUtil;
import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

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
//        long time = System.currentTimeMillis();
//        Admin admin = new Admin();
//        admin.setTime(time);
//        admin.setUsername("wangzhuanzhuan");
//        String encrypt = AESUtil.encrypt("123456789", DigestUtils.md5DigestAsHex(String.valueOf(admin.getTime()).getBytes()));
//        admin.setPassword(Base64.encodeBase64String(encrypt.getBytes()));
//        System.out.println(admin);
//        System.out.println(adminService.login(admin));
    }

    @Test
    void AdminServiceChangePassTest() {
//        Admin admin = new Admin();
//        admin.setUsername("admin");
//        admin.setPassword("111111");
//        System.out.println(adminService.changePassword(admin));
        System.out.println("/tendering/fdasd".matches("/tendering/.*"));
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
        System.out.println(bidService.selectBid(1, 10, new Bid()));
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
        Admin admin = new Admin();
        admin.setTime(time);
        admin.setUsername("admin");
        String encrypt = AESUtil.encrypt("111111", DigestUtils.md5DigestAsHex(String.valueOf(admin.getTime()).getBytes()));
        admin.setPassword(Base64.encodeBase64String(encrypt.getBytes()));
        System.out.println(admin);
//        System.out.println(enterpriseService.login(admin));
        System.out.println(adminService.login(admin));
    }

    @Test
    void EnterpriseServiceRegisterTest() throws Exception {
        long time = System.currentTimeMillis();
        Enterprise enterprise = new Enterprise();
        long phone = 123456789;
        int num = 30;
        String name = "有限公司";
        Set<String> set = new HashSet<>();
        while (num < 100) {
            enterprise.setTime(time);
            StringBuilder pre;
            while (true) {
                pre = new StringBuilder();
                for (int i = 0; i < 4; i++) {
                    pre.append(getRandomChar());
                }
                if (!set.contains(pre.toString())) {
                    set.add(pre.toString());
                    break;
                }
            }
            enterprise.setName(pre + name);
            enterprise.setPassword("123456789");
            enterprise.setAddress("山西省太原市尖草坪区" + num + "号");
            enterprise.setCertificates("http://file.erya.ychstudy.cn/group1/M00/00/06/rBAABV5rkTiAJ_ebAABB0Ez3K9g034.jpg");
            enterprise.setE_mail(phone + num + "@qq.com");
            enterprise.setLogo("http://file.erya.ychstudy.cn/group1/M00/00/06/rBAABV5rkTiAJ_ebAABB0Ez3K9g034.jpg");
            enterprise.setPhone(phone + "" + num);
            enterprise.setSite_url("https://www.baidu.com/");
//            System.out.println(enterpriseService.register(enterprise));
            num++;
        }
    }

    @Autowired
    private EmailService emailService;

    @Test
    void testSendEmail() {
//        emailService.sendSimpleMessage("yangchenghu@58.com","测试发送","111111");
    }

    @Autowired
    private TenderingProcessor tenderingProcessor;

    @Test
    void spiderTendering() {
//        tenderingProcessor.start("http://www.qianlima.com/zbgg/p5",1,1000);
//        tenderingProcessor.start("http://www.qianlima.com/zbgg/p7",1,1000);
    }

    @Test
    void testBid() {
        Bid bid = new Bid();
        bid.setContent("我要竞标");
        bid.setTime(new Date());
        bid.setSrc("http://file.erya.ychstudy.cn/group1/M00/00/06/rBAABV6y0dOAe2iGAACI4eIIV_E82.docx");
        for (int i = 0; i < 200; i++) {
            int e_id = (int) (Math.random() * 10) + 1;
            Enterprise info = enterpriseService.getInfo(e_id);
            int t_id = (int) (Math.random() * 100) + 1;
            Tendering tenderingInfo = tenderingService.getTenderingInfo(t_id);
            bid.setE_id(e_id);
            bid.setE_name(info.getName());
            bid.setT_title(tenderingInfo.getTitle());
            bid.setT_e_id(tenderingInfo.getE_id());
            bid.setT_id(tenderingInfo.getId());
//            bidService.insert(bid);
        }
//        tenderingProcessor.start("http://www.qianlima.com/zbgg/p5",1,1000);
//        tenderingProcessor.start("http://www.qianlima.com/zbgg/p7",1,1000);
    }

    private char getRandomChar() {
        String str = "";
        int hightPos; //
        int lowPos;

        Random random = new Random();

        hightPos = (176 + Math.abs(random.nextInt(39)));
        lowPos = (161 + Math.abs(random.nextInt(93)));

        byte[] b = new byte[2];
        b[0] = (Integer.valueOf(hightPos)).byteValue();
        b[1] = (Integer.valueOf(lowPos)).byteValue();

        try {
            str = new String(b, "GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            System.out.println("错误");
        }

        return str.charAt(0);
    }
}
