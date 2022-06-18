package com.segroup.hospitalsite.NucTest.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class NucTestApp implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("姓名")
    private String usrName;

    @TableId(value = "usr_id")
    @ApiModelProperty("身份证号")
    private String usrId;

    @ApiModelProperty("核酸检测类型:单检/混检")
    private String testType;

    @ApiModelProperty("核酸检测预约时间")
    private LocalDate testDate;


}
