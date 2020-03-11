package cn.ych.tendering.service.imp;

import cn.ych.tendering.mapper.ReportMapper;
import cn.ych.tendering.pojo.Bid;
import cn.ych.tendering.pojo.Report;
import cn.ych.tendering.service.ReportService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImp implements ReportService {
    private ReportMapper reportMapper;

    public ReportServiceImp(ReportMapper reportMapper) {
        this.reportMapper = reportMapper;
    }

    @Override
    public int insert(Report report) {
        return reportMapper.insert(report);
    }

    @Override
    public int modify(Report report) {
        return reportMapper.updateById(report);
    }

    @Override
    public IPage<Report> selectReport(int pageNo, int pageSize, String r_name, String b_name) {
        IPage<Report> page = new Page<>(pageNo, pageSize);
        QueryWrapper<Report> wrapper = new QueryWrapper<>();
        wrapper.like("r_name", "%" + r_name + "%");
        wrapper.like("b_name", "%" + b_name + "%");
        return reportMapper.selectPage(page, wrapper);
    }
}
