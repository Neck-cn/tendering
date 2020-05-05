package cn.ych.tendering.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("excellent_tendering")
@ApiModel
public class ExcellentTendering {
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "id")
    private int id;
    @ApiModelProperty(value = "企业id")
    private int e_id;
    @ApiModelProperty(value = "企业名称")
    private String e_name;
    @ApiModelProperty(value = "成功次数")
    private int win;
    @ApiModelProperty(value = "失败次数")
    private int fail;
    @ApiModelProperty(value = "总次数")
    private int sum;
    @ApiModelProperty(value = "成功率")
    private double win_rate;
    @ApiModelProperty(value = "失败了率")
    private double fail_rate;
}
