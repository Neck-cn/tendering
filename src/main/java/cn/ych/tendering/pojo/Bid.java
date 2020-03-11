package cn.ych.tendering.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("bid")
public class Bid {
    @TableId(type = IdType.AUTO)
    private int id;
    private int e_id;
    private int t_id;
    private String content;
    private Date time;
    private String status;
    private String src;
    private String name;
}
