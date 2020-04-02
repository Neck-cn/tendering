package cn.ych.tendering.controller;

import cn.ych.tendering.pojo.Tendering;
import cn.ych.tendering.service.TenderingService;
import cn.ych.tendering.vo.Result;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TenderingController {
    private TenderingService tenderingService;

    public TenderingController(TenderingService tenderingService) {
        this.tenderingService = tenderingService;
    }

    @ApiOperation("创建招标信息")
    @PostMapping("/tendering/insert")
    public ResponseEntity<Result> insert(@RequestBody Tendering tendering) {
        return ResponseEntity.status(HttpStatus.OK).body(new Result(tenderingService.insert(tendering)));
    }

    @ApiOperation("修改招标信息")
    @PutMapping("/tendering/modify")
    public ResponseEntity<Result> modify(@RequestBody Tendering tendering) {
        return ResponseEntity.status(HttpStatus.OK).body(new Result(tenderingService.modify(tendering)));
    }

    @ApiOperation("查询所有招标信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页面", required = true),
            @ApiImplicitParam(name = "pageSize", value = "每页大小", required = true),
    })
    @PostMapping("/tendering/{page}/{pageSize}")
    public ResponseEntity<Result> selectTendering(@RequestBody Tendering tendering, @PathVariable int page, @PathVariable int pageSize) {
        return ResponseEntity.status(HttpStatus.OK).body(new Result(tenderingService.selectTendering(page, pageSize, tendering)));
    }

    @ApiOperation("删除招标信息")
    @ApiImplicitParam(name = "id", value = "招标信息id", required = true)
    @PostMapping("/tendering/{tid}")
    public ResponseEntity<Result> deleteTendering(@PathVariable int tid) {
        return ResponseEntity.status(HttpStatus.OK).body(new Result(tenderingService.delete(tid)));
    }

    /**
     * @param t_id 招标信息id
     * @return Response
     */
    @ApiOperation("招标信息详情")
    @ApiImplicitParam(name = "id", value = "招标信息id", required = true)
    @GetMapping("/tendering/{t_id}")
    public ResponseEntity<Result> getTenderingInfo(@PathVariable int t_id) {
        return ResponseEntity.status(HttpStatus.OK).body(new Result(tenderingService.getTenderingInfo(t_id)));
    }
}
