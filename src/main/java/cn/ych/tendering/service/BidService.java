package cn.ych.tendering.service;

import cn.ych.tendering.pojo.Bid;
import com.baomidou.mybatisplus.core.metadata.IPage;

public interface BidService {
    int insert(Bid bid);

    int update(Bid bid);

    IPage<Bid> selectBid(int pageNo, int pageSize, Bid bid);

    Bid selectById(int id);

    int deleteById(int id);
}
