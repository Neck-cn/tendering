package cn.ych.tendering.controller;

import cn.ych.tendering.pojo.Enterprise;
import cn.ych.tendering.service.EnterpriseService;
import cn.ych.tendering.vo.Result;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
public class EnterpriseController {
    private EnterpriseService enterpriseService;
    private StringRedisTemplate stringRedisTemplate;

    public EnterpriseController(EnterpriseService enterpriseService, StringRedisTemplate stringRedisTemplate) {
        this.enterpriseService = enterpriseService;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @ApiOperation(value = "企业注册")
    @PostMapping("/enterprise/register")
    public ResponseEntity<Result> register(@RequestBody Enterprise enterprise) {
        String s = stringRedisTemplate.opsForValue().get(enterprise.getPhone());
        if (enterprise.getCode().equals(s)) {
            return ResponseEntity.status(HttpStatus.OK).body(new Result(enterpriseService.register(enterprise)));
        }
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new Result("验证码错误"));
    }

    @ApiOperation(value = "企业登录")
    @PostMapping("/enterprise/login")
    public ResponseEntity<Result> login(@RequestBody Enterprise enterprise) {
        Map<String, Object> login = enterpriseService.login(enterprise);
        if (login == null) {
            return ResponseEntity.status(HttpStatus.OK).body(new Result());
        }
        stringRedisTemplate.opsForValue().set(login.get("token").toString(), "1", Long.parseLong(login.get("expiretime").toString()) - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        return ResponseEntity.status(HttpStatus.OK).body(new Result(login));
    }

    @ApiOperation(value = "企业信息修改")
    @PutMapping("/enterprise/modify")
    public ResponseEntity<Result> modify(@RequestBody Enterprise enterprise) {
        return ResponseEntity.status(HttpStatus.OK).body(new Result(enterpriseService.modify(enterprise)));
    }

    @ApiOperation(value = "发送验证码")
    @ApiImplicitParam(name = "phone", value = "手机号", dataType = "array", required = true)
    @PostMapping("/enterprise/sendCode")
    public ResponseEntity<Result> sendCode(@RequestBody List<String> phone) {
        String res = enterpriseService.sendMsg(phone.get(0));
        if (res != null) {
            stringRedisTemplate.opsForValue().set(phone.get(0), res, 300000, TimeUnit.MILLISECONDS);
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(new Result("短信发送成功。"));
        }
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(new Result("短信发送失败。"));
    }

    @ApiOperation(value = "获取企业信息")
    @ApiImplicitParam(name = "id", value = "企业id", required = true)
    @GetMapping("/enterprise/info/{id}")
    public ResponseEntity<Result> getInfo(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(new Result(enterpriseService.getInfo(id)));
    }

    @ApiOperation("查询所有企业信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页面", required = true),
            @ApiImplicitParam(name = "pageSize", value = "每页大小", required = true),
    })
    @PostMapping("/enterprise/{page}/{pageSize}")
    public ResponseEntity<Result> selectEnterpriseList(@PathVariable int page, @PathVariable int pageSize, @RequestBody Enterprise enterprise) {
        return ResponseEntity.status(HttpStatus.OK).body(new Result(enterpriseService.selectEnterprise(page, pageSize, enterprise)));
    }
}
