package cn.ych.tendering.service;

import cn.ych.tendering.pojo.ExcellentBid;
import cn.ych.tendering.pojo.ExcellentTendering;
import com.baomidou.mybatisplus.core.metadata.IPage;

public interface ExcellentTenderingService {
    IPage<ExcellentTendering> selectExcellent(int page, int pageSize, ExcellentTendering excellentTendering);
}
