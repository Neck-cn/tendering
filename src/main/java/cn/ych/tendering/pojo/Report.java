package cn.ych.tendering.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;


@Data
@TableName("report")
public class Report {
    @TableId(type = IdType.AUTO)
    private int id;
    private int bid;
    private String content;
    private int e_id;
    private Date time;
    private String r_name;
    private String e_name;
    private String status;
}
