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
 * @since 2022-05-26
 */
@Getter
@Setter
@ApiModel(value = "Workday对象", description = "")
public class Workday implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "wid", type = IdType.AUTO)
    private Integer wid;

    @ApiModelProperty("医生id")
    private Integer did;

    @ApiModelProperty("医生工作日，星期几")
    private String workTime;

    @ApiModelProperty("医生工作日的上午或下午")
    private String ampm;

    @ApiModelProperty("医生这天上午或下午的号源数量")
    private Integer nsnum;

    @ApiModelProperty("状态：已满，预约，停诊")
    private String state;


}
