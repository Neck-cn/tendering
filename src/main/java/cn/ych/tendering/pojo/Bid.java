package cn.ych.tendering.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@TableName("bid")
@ApiModel
public class Bid {
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "竞标信息id")
    private int id;
    @ApiModelProperty(value = "竞标企业id")
    private int e_id;
    @ApiModelProperty(value = "竞标项目id")
    private int t_id;
    @ApiModelProperty(value = "竞标信息内容")
    private String content;
    @ApiModelProperty(value = "竞标信息时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date time;
    @ApiModelProperty(value = "竞标书地址")
    private String src;
    @ApiModelProperty(value = "竞标企业名称")
    private String e_name;
    @ApiModelProperty(value = "招标项目标题")
    private String t_title;
}
