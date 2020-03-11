package cn.ych.tendering.controller;

import cn.ych.tendering.pojo.Report;
import cn.ych.tendering.service.ReportService;
import cn.ych.tendering.vo.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReportController {
    private ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping("/report/insert")
    public ResponseEntity<Result> insert(@RequestBody Report report) {
        return ResponseEntity.status(HttpStatus.OK).body(new Result(reportService.insert(report)));
    }

    @PutMapping("/report/modify")
    public ResponseEntity<Result> modify(@RequestBody Report report) {
        return ResponseEntity.status(HttpStatus.OK).body(new Result(reportService.modify(report)));
    }

    @GetMapping("/report/{page}/{pageSize}")
    public ResponseEntity<Result> selectTendering(@RequestParam String r_name, @RequestParam String b_name, @PathVariable int page, @PathVariable int pageSize) {
        return ResponseEntity.status(HttpStatus.OK).body(new Result(reportService.selectReport(page, pageSize, r_name, b_name)));
    }
}
