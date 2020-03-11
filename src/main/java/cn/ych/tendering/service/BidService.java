package cn.ych.tendering.service;

import cn.ych.tendering.pojo.Bid;
import cn.ych.tendering.pojo.Tendering;
import com.baomidou.mybatisplus.core.metadata.IPage;

public interface BidService {
    int insert(Bid bid);

    int update(Bid bid);

    IPage<Bid> selectBid(int pageNo, int pageSize, String search);
}
