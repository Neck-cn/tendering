package cn.ych.tendering.service.imp;

import cn.ych.tendering.mapper.BidMapper;
import cn.ych.tendering.pojo.Bid;
import cn.ych.tendering.service.BidService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

@Service
public class BidServiceImp implements BidService {
    private BidMapper bidMapper;

    public BidServiceImp(BidMapper bidMapper) {
        this.bidMapper = bidMapper;
    }

    @Override
    public int insert(Bid bid) {
        return bidMapper.insert(bid);
    }

    @Override
    public int update(Bid bid) {
        return bidMapper.updateById(bid);
    }

    @Override
    public IPage<Bid> selectBid(int pageNo, int pageSize, String search) {
        IPage<Bid> page = new Page<>(pageNo, pageSize);
        QueryWrapper<Bid> wrapper = new QueryWrapper<>();
        wrapper.eq("status", "1");
        wrapper.like("name", "%" + search + "%");
        return bidMapper.selectPage(page, wrapper);
    }

    @Override
    public IPage<Bid> selectBidByeId(int e_id, int pageNo, int pageSize, String query) {
        IPage<Bid> page = new Page<>(pageNo, pageSize);
        QueryWrapper<Bid> wrapper = new QueryWrapper<>();
        wrapper.eq("e_id", e_id);
        wrapper.like("name", "%" + query + "%");
        return bidMapper.selectPage(page, wrapper);
    }

}
