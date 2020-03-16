package cn.ych.tendering.service;

import cn.ych.tendering.pojo.Report;
import com.baomidou.mybatisplus.core.metadata.IPage;

public interface ReportService {
    int insert(Report report);

    int modify(Report report);

    IPage<Report> selectReport(Report report, int pageNo, int pageSize);
}
