package cn.ych.tendering.controller;

import cn.ych.tendering.pojo.Admin;
import cn.ych.tendering.service.AdminService;
import cn.ych.tendering.vo.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
public class AdminController {
    private AdminService adminService;
    private StringRedisTemplate stringRedisTemplate;

    public AdminController(AdminService adminService, StringRedisTemplate stringRedisTemplate) {
        this.adminService = adminService;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @ApiOperation("管理员登录")
    @PostMapping("/admin/login")
    public ResponseEntity<Result> login(@RequestBody Admin admin) {
        Map<String, Object> login = adminService.login(admin);
        if (login == null) {
            return ResponseEntity.status(HttpStatus.OK).body(new Result());
        }
        stringRedisTemplate.opsForValue().set(login.get("token").toString(), "1", Long.parseLong(login.get("expiretime").toString()) - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        Result result = new Result(login);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation("管理员修改密码")
    @PutMapping("/admin/changePassword")
    public ResponseEntity<Result> changePassword(@RequestBody Admin admin) {
        Result result = new Result(adminService.changePassword(admin));
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
