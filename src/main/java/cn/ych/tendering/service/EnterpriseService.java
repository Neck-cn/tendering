package cn.ych.tendering.service;

import cn.ych.tendering.pojo.Enterprise;

import java.util.Map;

public interface EnterpriseService {
    int register(Enterprise enterprise);

    Map<String,String> login(Enterprise enterprise);

    int modify(Enterprise enterprise);

    String sendMsg(String phone);

    Enterprise getInfo(int id);
}
