package com.segroup.hospitalsite.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author zheng
 * @since 2022-06-12
 */
@Getter
@Setter
@ApiModel(value = "Record对象", description = "")
public class Record implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("预订号")
    @TableId(value = "rid", type = IdType.AUTO)
    private Integer rid;

    @ApiModelProperty("用户id")
    private Integer uid;

    @ApiModelProperty("工作日id")
    private Integer wid;

    @ApiModelProperty("医生id")
    private String did;

    @ApiModelProperty("就诊序号")
    private Integer serialnumber;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("性别")
    private String sex;

    @ApiModelProperty("年龄")
    private Integer age;

    @ApiModelProperty("身份证号")
    private String pwd;

    @ApiModelProperty("预约时间")
    private String orderData;

    @ApiModelProperty("就诊日期")
    private String visitData;

    @ApiModelProperty("预约状态：成功，取消，完成，爽约")
    private String state;


}
