package com.segroup.hospitalsite.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
@ApiModel(value = "Record对象", description = "")
public class Record implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("预订号")
    @TableId(value = "rid", type = IdType.AUTO)
    private Integer rid;

    @ApiModelProperty("用户id")
    private Integer pid;

    @ApiModelProperty("工作日id")
    private Integer wid;

    @ApiModelProperty("医生id")
    private Integer did;

    @ApiModelProperty("就诊序号")
    private Integer serialnumber;

    @ApiModelProperty("预约时间")
    private LocalDateTime orderData;

    @ApiModelProperty("就诊日期")
    private LocalTime visitData;

    @ApiModelProperty("就诊上午/下午")
    private String visitAmpm;

    @ApiModelProperty("就诊时间")
    private LocalTime visitTime;

    @ApiModelProperty("预约状态：成功，取消，完成，爽约")
    private String state;


}
