package com.segroup.hospitalsite.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 核酸检测预约表
 * </p>
 *
 * @author Tristan
 * @since 2022-05-25
 */
@Getter
@Setter
@TableName("nuc_testApp")
@ApiModel(value = "NucTestapp对象", description = "核酸检测预约表")
public class NucTestapp implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("姓名")
    private String usrName;

    @ApiModelProperty("身份证号")
    private String usrId;

    @ApiModelProperty("核酸检测类型:0单检/1混检")
    private Integer testType;

    @ApiModelProperty("核酸检测预约时间")
    private LocalDate testDate;


}
