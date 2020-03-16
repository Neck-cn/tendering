package cn.ych.tendering.service.imp;

import cn.ych.tendering.mapper.BidMapper;
import cn.ych.tendering.pojo.Bid;
import cn.ych.tendering.service.BidService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;

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
    public IPage<Bid> selectBid(int pageNo, int pageSize, Bid bid) {
        IPage<Bid> page = new Page<>(pageNo, pageSize);
        QueryWrapper<Bid> wrapper = new QueryWrapper<>();
        if (bid.getId() != 0) {
            wrapper.eq("id", bid.getId());
        }
        if (bid.getContent() != null) {
            wrapper.like("content", "%" + bid.getContent() + "%");
        }
        if (bid.getE_id() != 0) {
            wrapper.eq("e_id", bid.getE_id());
        }
        if (bid.getName() != null) {
            wrapper.like("name", "%" + bid.getName() + "%");
        }
        if (bid.getSrc() != null) {
            wrapper.like("src", "%" + bid.getSrc() + "%");
        }
        if (bid.getTime() != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            wrapper.eq("time", sdf.format(bid.getTime()));
        }
        if (bid.getT_id() != 0) {
            wrapper.eq("status", bid.getT_id());
        }
        return bidMapper.selectPage(page, wrapper);
    }

    @Override
    public Bid selectById(int id) {
        return bidMapper.selectById(id);
    }

    @Override
    public int deleteById(int id) {
        return bidMapper.deleteById(id);
    }

}
