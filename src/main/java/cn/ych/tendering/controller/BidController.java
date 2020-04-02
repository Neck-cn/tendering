package cn.ych.tendering.controller;

import cn.ych.tendering.pojo.Bid;
import cn.ych.tendering.service.BidService;
import cn.ych.tendering.vo.Result;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BidController {
    private BidService bidService;

    public BidController(BidService bidService) {
        this.bidService = bidService;
    }

    @ApiOperation("创建竞标信息")
    @PostMapping("/bid/insert")
    public ResponseEntity<Result> insert(@RequestBody Bid bid) {
        return ResponseEntity.status(HttpStatus.OK).body(new Result(bidService.insert(bid)));
    }

    @ApiOperation("修改竞标信息")
    @PutMapping("/bid/modify")
    public ResponseEntity<Result> modify(@RequestBody Bid bid) {
        return ResponseEntity.status(HttpStatus.OK).body(new Result(bidService.update(bid)));
    }

    /**
     * 查询所有竞标信息
     *
     * @return Response
     */
    @ApiOperation("查询所有竞标信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页面", required = true),
            @ApiImplicitParam(name = "pageSize", value = "每页大小", required = true),
    })
    @PostMapping("/bid/{page}/{pageSize}")
    public ResponseEntity<Result> selectBidList(@RequestBody Bid bid, @PathVariable int page, @PathVariable int pageSize) {
        return ResponseEntity.status(HttpStatus.OK).body(new Result(bidService.selectBid(page, pageSize, bid)));
    }

    @ApiOperation("竞标信息详情")
    @ApiImplicitParam(name = "id", value = "竞标信息id", required = true)
    @GetMapping("/bid/{id}")
    public ResponseEntity<Result> selectBidInfo(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(new Result(bidService.selectById(id)));
    }

    @ApiOperation("删除竞标信息")
    @ApiImplicitParam(name = "id", value = "竞标信息id", required = true)
    @PostMapping("/bid/{id}")
    public ResponseEntity<Result> deleteBid(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(new Result(bidService.deleteById(id)));
    }
}
