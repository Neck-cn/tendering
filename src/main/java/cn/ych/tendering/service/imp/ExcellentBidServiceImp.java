package cn.ych.tendering.service.imp;

import cn.ych.tendering.mapper.ExcellentBidMapper;
import cn.ych.tendering.pojo.ExcellentBid;
import cn.ych.tendering.service.ExcellentBidService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class ExcellentBidServiceImp implements ExcellentBidService {
    private ExcellentBidMapper excellentBidMapper;

    public ExcellentBidServiceImp(ExcellentBidMapper excellentBidMapper) {
        this.excellentBidMapper = excellentBidMapper;
    }

    @Override
    public IPage<ExcellentBid> selectExcellent(int pageNo, int pageSize, ExcellentBid excellentBid) {
        IPage<ExcellentBid> page = new Page<>(pageNo, pageSize);
        QueryWrapper<ExcellentBid> wrapper = new QueryWrapper<>();
        if (excellentBid.getId() != 0) {
            wrapper.eq("id", excellentBid.getId());
        }
        if (excellentBid.getE_id() != 0) {
            wrapper.eq("e_id", excellentBid.getE_id());
        }
        if (StringUtils.isNotEmpty(excellentBid.getName())) {
            wrapper.like("name", "%" + excellentBid.getName() + "%");
        }
        return excellentBidMapper.selectPage(page, wrapper);
    }
}
