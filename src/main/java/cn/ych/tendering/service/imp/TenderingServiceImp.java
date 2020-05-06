package cn.ych.tendering.service.imp;

import cn.ych.tendering.mapper.TenderingMapper;
import cn.ych.tendering.pojo.Tendering;
import cn.ych.tendering.service.TenderingService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;

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
    public IPage<Tendering> selectTendering(int pageNo, int pageSize, Tendering tendering) {
        IPage<Tendering> page = new Page<>(pageNo, pageSize);
        QueryWrapper<Tendering> wrapper = new QueryWrapper<>();
        if (tendering.getId() != 0) {
            wrapper.eq("id", tendering.getId());
        }
        if (tendering.getE_id() != 0) {
            wrapper.eq("e_id", tendering.getE_id());
        }
        if (StringUtils.isNotEmpty(tendering.getContent())) {
            wrapper.like("content", "%" + tendering.getContent() + "%");
        }
        if (tendering.getEnd_time() != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            wrapper.ge("end_time", sdf.format(tendering.getEnd_time()));
        }
        if (StringUtils.isNotEmpty(tendering.getName())) {
            wrapper.like("name", "%" + tendering.getName() + "%");
        }
        if (StringUtils.isNotEmpty(tendering.getSrc())) {
            wrapper.like("src", "%" + tendering.getSrc() + "%");
        }
        if (tendering.getStart_time() != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            wrapper.le("start_time", sdf.format(tendering.getStart_time()));
        }
        if (tendering.getStatus() != 0) {
            wrapper.eq("status", tendering.getStatus());
        }
        if (StringUtils.isNotEmpty(tendering.getTitle())) {
            wrapper.like("title", "%" + tendering.getTitle() + "%");
        }
        if (tendering.getWin_id() != 0) {
            wrapper.eq("win_id", tendering.getWin_id());
        }
        return tenderingMapper.selectPage(page, wrapper);
    }

    @Override
    public Tendering getTenderingInfo(int t_id) {
        return tenderingMapper.selectById(t_id);
    }

    @Override
    public int delete(int tid) {
        return tenderingMapper.deleteById(tid);
    }
}
