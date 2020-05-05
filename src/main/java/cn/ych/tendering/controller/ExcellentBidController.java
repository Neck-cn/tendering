package cn.ych.tendering.controller;

import cn.ych.tendering.pojo.ExcellentBid;
import cn.ych.tendering.service.ExcellentBidService;
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
public class ExcellentBidController {
    private ExcellentBidService excellentBidService;

    public ExcellentBidController(ExcellentBidService excellentBidService) {
        this.excellentBidService = excellentBidService;
    }

    @ApiOperation("查询所有优秀企业信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页面", required = true),
            @ApiImplicitParam(name = "pageSize", value = "每页大小", required = true),
    })
    @PostMapping("/excellent/bid/{page}/{pageSize}")
    public ResponseEntity<Result> selectEnterpriseList(@PathVariable int page, @PathVariable int pageSize, @RequestBody ExcellentBid excellentBid) {
        return ResponseEntity.status(HttpStatus.OK).body(new Result(excellentBidService.selectExcellent(page, pageSize, excellentBid)));
    }
}
