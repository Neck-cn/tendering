package cn.ych.tendering.service;

import cn.ych.tendering.pojo.Enterprise;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.Map;

public interface EnterpriseService {
    int register(Enterprise enterprise);

    Map<String,Object> login(Enterprise enterprise);

    int modify(Enterprise enterprise);

    String sendMsg(String phone);

    Enterprise getInfo(int id);

    IPage<Enterprise> selectEnterprise(int page, int pageSize, Enterprise enterprise);
}
