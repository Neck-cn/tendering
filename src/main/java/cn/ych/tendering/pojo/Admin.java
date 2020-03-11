package cn.ych.tendering.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.awt.*;

@Data
@TableName("admin")
public class Admin {
    @TableId
    private String username;
    private String password;
    @TableField(exist = false)
    private long time;
}
