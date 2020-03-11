package cn.ych.tendering.service.imp;

import cn.ych.tendering.mapper.TenderingMapper;
import cn.ych.tendering.pojo.Tendering;
import cn.ych.tendering.service.TenderingService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TenderingServiceImp implements TenderingService {
    private TenderingMapper tenderingMapper;

    public TenderingServiceImp(TenderingMapper tenderingMapper) {
        this.tenderingMapper = tenderingMapper;
    }

    @Override
    public int insert(Tendering tendering) {
        return tenderingMapper.insert(tendering);
    }

    @Override
    public int modify(Tendering tendering) {
        return tenderingMapper.updateById(tendering);
    }

    @Override
    public IPage<Tendering> selectTendering(int pageNo, int pageSize, String search, String status, boolean all) {
        IPage<Tendering> page = new Page<>(pageNo, pageSize);
        QueryWrapper<Tendering> wrapper = new QueryWrapper<>();
        wrapper.eq("status", status);
        if (!all) {
            wrapper.le("end_time", new Date());
        }
        wrapper.like("title", "%" + search + "%");
        return tenderingMapper.selectPage(page, wrapper);
    }
}
