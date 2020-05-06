package cn.ych.tendering.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("enterprise")
@ApiModel
public class Enterprise {
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "企业id")
    private int id;
    @ApiModelProperty(value = "企业用户名")
    private String username;
    @ApiModelProperty(value = "企业密码")
    private String password;
    @ApiModelProperty(value = "企业网址")
    private String site_url;
    @ApiModelProperty(value = "企业地址")
    private String address;
    @ApiModelProperty(value = "企业手机")
    private String phone;
    @ApiModelProperty(value = "验证码")
    @TableField(exist = false)
    private String code;
    @ApiModelProperty(value = "当前时间")
    @TableField(exist = false)
    private long time;
    @ApiModelProperty(value = "企业资质证书地址")
    private String certificates;
    @ApiModelProperty(value = "电子邮件")
    private String e_mail;
    @ApiModelProperty(value = "企业logo")
    private String logo;
}
