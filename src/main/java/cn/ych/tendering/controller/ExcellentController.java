package cn.ych.tendering.controller;

import cn.ych.tendering.pojo.Excellent;
import cn.ych.tendering.service.ExcellentService;
import cn.ych.tendering.vo.Result;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExcellentController {
    private ExcellentService excellentService;

    public ExcellentController(ExcellentService excellentService) {
        this.excellentService = excellentService;
    }

    @ApiOperation("查询所有优秀企业信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页面", required = true),
            @ApiImplicitParam(name = "pageSize", value = "每页大小", required = true),
    })
    @PostMapping("/excellent/{page}/{pageSize}")
    public ResponseEntity<Result> selectEnterpriseList(@PathVariable int page, @PathVariable int pageSize, @RequestBody Excellent excellent) {
        return ResponseEntity.status(HttpStatus.OK).body(new Result(excellentService.selectExcellent(page, pageSize, excellent)));
    }
}
