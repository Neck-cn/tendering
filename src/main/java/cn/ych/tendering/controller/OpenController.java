package cn.ych.tendering.controller;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class OpenController {
    private StringRedisTemplate stringRedisTemplate;

    public OpenController(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @PostMapping("open/check/{code}/{phone}")
    public ResponseEntity<Boolean> checkCode(@PathVariable("code") String code, @PathVariable("phone") String phone) {
        if (Objects.equals(stringRedisTemplate.opsForValue().get(phone), code)) {
            return ResponseEntity.status(HttpStatus.OK).body(true);
        }
        return ResponseEntity.status(HttpStatus.OK).body(false);
    }
}
