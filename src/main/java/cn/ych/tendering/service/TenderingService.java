package cn.ych.tendering.service;

import cn.ych.tendering.pojo.Tendering;
import com.baomidou.mybatisplus.core.metadata.IPage;

public interface TenderingService {
    int insert(Tendering tendering);

    int modify(Tendering tendering);

    IPage<Tendering> selectTendering(int pageNo, int pageSize, Tendering tendering);

    Tendering getTenderingInfo(int t_id);

    int delete(int tid);
}
