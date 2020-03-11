package cn.ych.tendering.controller;

import cn.ych.tendering.pojo.Tendering;
import cn.ych.tendering.service.TenderingService;
import cn.ych.tendering.vo.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TenderingController {
    private TenderingService tenderingService;

    public TenderingController(TenderingService tenderingService) {
        this.tenderingService = tenderingService;
    }

    @PostMapping("/tendering/insert")
    public ResponseEntity<Result> insert(@RequestBody Tendering tendering) {
        return ResponseEntity.status(HttpStatus.OK).body(new Result(tenderingService.insert(tendering)));
    }

    @PutMapping("/tendering/modify")
    public ResponseEntity<Result> modify(@RequestBody Tendering tendering) {
        return ResponseEntity.status(HttpStatus.OK).body(new Result(tenderingService.modify(tendering)));
    }

    @GetMapping("/tendering/{page}/{pageSize}")
    public ResponseEntity<Result> selectTendering(@RequestParam String query, @PathVariable int page, @PathVariable int pageSize) {
        return ResponseEntity.status(HttpStatus.OK).body(new Result(tenderingService.selectTendering(page, pageSize, query, "1", false)));
    }
}
