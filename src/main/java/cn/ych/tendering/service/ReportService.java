package cn.ych.tendering.service;

import cn.ych.tendering.pojo.Report;
import com.baomidou.mybatisplus.core.metadata.IPage;

public interface ReportService {
    int insert(Report report);

    int modify(Report report);

    IPage<Report> selectReport(int pageNo, int pageSize,String r_name,String b_name);
}
