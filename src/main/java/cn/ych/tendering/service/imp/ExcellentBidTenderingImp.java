package cn.ych.tendering.service.imp;

import cn.ych.tendering.mapper.ExcellentTenderingMapper;
import cn.ych.tendering.pojo.ExcellentTendering;
import cn.ych.tendering.service.ExcellentTenderingService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class ExcellentBidTenderingImp implements ExcellentTenderingService {
    private ExcellentTenderingMapper excellentTenderingMapper;

    public ExcellentBidTenderingImp(ExcellentTenderingMapper excellentTenderingMapper) {
        this.excellentTenderingMapper = excellentTenderingMapper;
    }

    @Override
    public IPage<ExcellentTendering> selectExcellent(int pageNo, int pageSize, ExcellentTendering excellentTendering) {
        IPage<ExcellentTendering> page = new Page<>(pageNo, pageSize);
        QueryWrapper<ExcellentTendering> wrapper = new QueryWrapper<>();
        if (excellentTendering.getId() != 0) {
            wrapper.eq("id", excellentTendering.getId());
        }
        if (excellentTendering.getE_id() != 0) {
            wrapper.eq("e_id", excellentTendering.getE_id());
        }
        if (StringUtils.isNotEmpty(excellentTendering.getE_name())) {
            wrapper.like("e_name", "%" + excellentTendering.getE_name() + "%");
        }
        if (excellentTendering.getWin() != 0) {
            wrapper.eq("win", excellentTendering.getWin());
        }
        if (excellentTendering.getFail() != 0) {
            wrapper.eq("fail", excellentTendering.getFail());
        }
        if (excellentTendering.getSum() != 0) {
            wrapper.eq("sum", excellentTendering.getSum());
        }
//        if (Double.compare(excellentTendering.getWin_rate(), 0) == 0) {
//            wrapper.eq("win_rate", excellentTendering.getWin_rate());
//        }
//        if (Double.compare(excellentTendering.getFail_rate(), 0) == 0) {
//            wrapper.eq("fail_rate", excellentTendering.getFail_rate());
//        }
        wrapper.orderByDesc("id");
        return excellentTenderingMapper.selectPage(page, wrapper);
    }
}
