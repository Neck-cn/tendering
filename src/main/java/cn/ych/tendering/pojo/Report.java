package cn.ych.tendering.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;


@Data
@TableName("report")
public class Report {
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "举报id")
    private int id;
    @ApiModelProperty(value = "竞标信息id")
    private int bid;
    @ApiModelProperty(value = "举报内容")
    private String content;
    @ApiModelProperty(value = "举报企业id")
    private int e_id;
    @ApiModelProperty(value = "举报时间")
//    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date time;
    @ApiModelProperty(value = "举报企业名")
    private String r_name;
    @ApiModelProperty(value = "被举报企业名")
    private String e_name;
}
