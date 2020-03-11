package cn.ych.tendering.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("enterprise")
public class Enterprise {
    @TableId(type = IdType.AUTO)
    private int id;
    private String username;
    private String password;
    private String name;
    private String address;
    private String phone;
    private String status;
    @TableField(exist = false)
    private String code;
    @TableField(exist = false)
    private long time;
    private String certificates;
}
