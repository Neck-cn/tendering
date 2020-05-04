package cn.ych.tendering.service;

import cn.ych.tendering.pojo.Excellent;
import com.baomidou.mybatisplus.core.metadata.IPage;

public interface ExcellentService {
    IPage<Excellent> selectExcellent(int page, int pageSize, Excellent excellent);
}
