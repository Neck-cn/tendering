package cn.ych.tendering.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("excellent")
@ApiModel
public class Excellent {
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "id")
    private int id;
    @ApiModelProperty(value = "企业id")
    private int e_id;
    @ApiModelProperty(value = "企业名称")
    private String name;
}
