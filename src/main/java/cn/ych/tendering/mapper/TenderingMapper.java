package cn.ych.tendering.mapper;

import cn.ych.tendering.pojo.Tendering;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
public interface TenderingMapper extends BaseMapper<Tendering> {
}
