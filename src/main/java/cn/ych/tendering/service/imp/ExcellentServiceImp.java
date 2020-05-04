package cn.ych.tendering.service.imp;

import cn.ych.tendering.mapper.ExcellentMapper;
import cn.ych.tendering.pojo.Excellent;
import cn.ych.tendering.service.ExcellentService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class ExcellentServiceImp implements ExcellentService {
    private ExcellentMapper excellentMapper;

    public ExcellentServiceImp(ExcellentMapper excellentMapper) {
        this.excellentMapper = excellentMapper;
    }

    @Override
    public IPage<Excellent> selectExcellent(int pageNo, int pageSize, Excellent excellent) {
        IPage<Excellent> page = new Page<>(pageNo, pageSize);
        QueryWrapper<Excellent> wrapper = new QueryWrapper<>();
        if (excellent.getId() != 0) {
            wrapper.eq("id", excellent.getId());
        }
        if (excellent.getE_id() != 0) {
            wrapper.eq("e_id", excellent.getE_id());
        }
        if (StringUtils.isNotEmpty(excellent.getName())) {
            wrapper.like("name", "%" + excellent.getName() + "%");
        }
        return excellentMapper.selectPage(page, wrapper);
    }
}
