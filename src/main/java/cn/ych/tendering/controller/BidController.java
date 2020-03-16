package cn.ych.tendering.controller;

import cn.ych.tendering.pojo.Bid;
import cn.ych.tendering.service.BidService;
import cn.ych.tendering.vo.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BidController {
    private BidService bidService;

    public BidController(BidService bidService) {
        this.bidService = bidService;
    }

    @PostMapping("/bid/insert")
    public ResponseEntity<Result> insert(@RequestBody Bid bid) {
        return ResponseEntity.status(HttpStatus.OK).body(new Result(bidService.insert(bid)));
    }


    @PutMapping("/bid/modify")
    public ResponseEntity<Result> modify(@RequestBody Bid bid) {
        return ResponseEntity.status(HttpStatus.OK).body(new Result(bidService.update(bid)));
    }

    /**
     * 查询所有竞标信息
     *
     * @return Response
     */
    @PostMapping("/bid/{page}/{pageSize}")
    public ResponseEntity<Result> selectBidList(@RequestBody Bid bid, @PathVariable int page, @PathVariable int pageSize) {
        return ResponseEntity.status(HttpStatus.OK).body(new Result(bidService.selectBid(page, pageSize, bid)));
    }

    @GetMapping("/bid/{id}")
    public ResponseEntity<Result> selectBidInfo(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(new Result(bidService.selectById(id)));
    }

}
