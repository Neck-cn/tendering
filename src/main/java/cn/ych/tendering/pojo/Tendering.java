package cn.ych.tendering.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("tendering")
public class Tendering {
    @TableId(type = IdType.AUTO)
    private int id;
    private String content;
    private int e_id;
    private String status;
    private Date start_time;
    private Date end_time;
    private int win_id;
    private String title;
    private String src;
    private String name;
}
