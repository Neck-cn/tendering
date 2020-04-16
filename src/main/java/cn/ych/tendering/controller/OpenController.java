package cn.ych.tendering.controller;

import cn.ych.tendering.service.EmailService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class OpenController {
    private StringRedisTemplate stringRedisTemplate;
    private EmailService emailService;

    public OpenController(StringRedisTemplate stringRedisTemplate, EmailService emailService) {
        this.stringRedisTemplate = stringRedisTemplate;
        this.emailService = emailService;
    }

    @ApiOperation("校验验证码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "验证码", required = true),
            @ApiImplicitParam(name = "phone", value = "手机号", required = true),
    })
    @PostMapping("/open/check")
    public ResponseEntity<Boolean> checkCode(@RequestParam("code") String code, @RequestParam("phone") String phone) {
        if (Objects.equals(stringRedisTemplate.opsForValue().get(phone), code)) {
            return ResponseEntity.status(HttpStatus.OK).body(true);
        }
        return ResponseEntity.status(HttpStatus.OK).body(false);
    }

    @ApiOperation("邮件发送接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "address", value = "邮件地址", required = true),
            @ApiImplicitParam(name = "subject", value = "主题", required = true),
            @ApiImplicitParam(name = "text", value = "内容", required = true),
    })
    @PostMapping("/open/sendEmail")
    public ResponseEntity<Boolean> sendEmail(@RequestParam("address") String address, @RequestParam("subject") String subject, @RequestParam("text") String text) {
        emailService.sendSimpleMessage(address, subject, text);
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }
}
