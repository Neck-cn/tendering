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
     * @param query 招标企业名
     * @param page 当前页
     * @param pageSize 每页大小
     * @return Response
     */
    @GetMapping("/bid/{page}/{pageSize}")
    public ResponseEntity<Result> selectTendering(@RequestParam String query, @PathVariable int page, @PathVariable int pageSize) {
        return ResponseEntity.status(HttpStatus.OK).body(new Result(bidService.selectBid(page, pageSize, query)));
    }

    /**
     *企业查询自己的竞标信息
     * @param e_id 竞标企业id
     * @param query 招标企业名
     * @param page 当前页
     * @param pageSize 每页大小
     * @return Response
     */
    @GetMapping("/bid/{e_id}/{page}/{pageSize}")
    public ResponseEntity<Result> selectTendering(@RequestParam String query, @PathVariable int e_id, @PathVariable int page, @PathVariable int pageSize) {
        return ResponseEntity.status(HttpStatus.OK).body(new Result(bidService.selectBidByeId(e_id,page, pageSize, query)));
    }
}
