package cn.ych.tendering.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("excellent_bid")
@ApiModel
public class ExcellentBid {
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "id")
    private int id;
    @ApiModelProperty(value = "企业id")
    private int e_id;
    @ApiModelProperty(value = "企业名称")
    private String name;
    @ApiModelProperty(value = "分数")
    private int score;
}
