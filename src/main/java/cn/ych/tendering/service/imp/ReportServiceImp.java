package cn.ych.tendering.service.imp;

import cn.ych.tendering.mapper.ReportMapper;
import cn.ych.tendering.pojo.Report;
import cn.ych.tendering.service.ReportService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;

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
    public IPage<Report> selectReport(Report report, int pageNo, int pageSize) {
        IPage<Report> page = new Page<>(pageNo, pageSize);
        QueryWrapper<Report> wrapper = new QueryWrapper<>();
        if (report.getId() != 0) {
            wrapper.eq("id", report.getId());
        }
        if (report.getBid() != 0) {
            wrapper.eq("bid", report.getBid());
        }
        if (report.getE_id() != 0) {
            wrapper.eq("e_id", report.getE_id());
        }
        if (report.getContent() != null) {
            wrapper.like("content", "%" + report.getContent() + "%");
        }
        if (report.getE_name() != null) {
            wrapper.like("b_name", "%" + report.getE_name() + "%");
        }
        if (report.getR_name() != null) {
            wrapper.like("r_name", "%" + report.getR_name() + "%");
        }
        if (report.getTime() != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            wrapper.eq("time", sdf.format(report.getTime()));
        }
        return reportMapper.selectPage(page, wrapper);
    }
}
