package cn.ych.tendering.controller;

import cn.ych.tendering.pojo.Report;
import cn.ych.tendering.service.ReportService;
import cn.ych.tendering.vo.Result;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReportController {
    private ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @ApiOperation("创建举报信息")
    @PostMapping("/report/insert")
    public ResponseEntity<Result> insert(@RequestBody Report report) {
        return ResponseEntity.status(HttpStatus.OK).body(new Result(reportService.insert(report)));
    }

    @ApiOperation("修改举报信息")
    @PutMapping("/report/modify")
    public ResponseEntity<Result> modify(@RequestBody Report report) {
        return ResponseEntity.status(HttpStatus.OK).body(new Result(reportService.modify(report)));
    }


    @ApiOperation("查询所有举报信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页面", required = true),
            @ApiImplicitParam(name = "pageSize", value = "每页大小", required = true),
    })
    @GetMapping("/report/{page}/{pageSize}")
    public ResponseEntity<Result> selectReport(@RequestBody Report report, @PathVariable int page, @PathVariable int pageSize) {
        return ResponseEntity.status(HttpStatus.OK).body(new Result(reportService.selectReport(report, page, pageSize)));
    }
}
