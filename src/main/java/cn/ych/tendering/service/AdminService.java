package cn.ych.tendering.service;

import cn.ych.tendering.pojo.Admin;

import java.util.Map;

public interface AdminService {
    Map<String,Object> login(Admin admin);

    int changePassword(Admin admin);

    int register(Admin admin);
}
