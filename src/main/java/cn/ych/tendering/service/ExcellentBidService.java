package cn.ych.tendering.service;

import cn.ych.tendering.pojo.ExcellentBid;
import com.baomidou.mybatisplus.core.metadata.IPage;

public interface ExcellentBidService {
    IPage<ExcellentBid> selectExcellent(int page, int pageSize, ExcellentBid excellentBid);
}
