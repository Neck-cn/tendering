package cn.ych.tendering.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@TableName("tendering")
public class Tendering {
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "招标信息id")
    private int id;
    @ApiModelProperty(value = "招标内容")
    private String content;
    @ApiModelProperty(value = "招标企业id")
    private int e_id;
    @ApiModelProperty(value = "招标状态")
    private String status;
    @ApiModelProperty(value = "招标开始时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date start_time;
    @ApiModelProperty(value = "招标结束时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date end_time;
    @ApiModelProperty(value = "中标企业id")
    private int win_id;
    @ApiModelProperty(value = "招标标题")
    private String title;
    @ApiModelProperty(value = "招标书地址")
    private String src;
    @ApiModelProperty(value = "招标企业名")
    private String name;
}
