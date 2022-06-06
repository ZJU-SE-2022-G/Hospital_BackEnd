package com.segroup.hospitalsite.IntroductionApp.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 医生信息介绍表
 * </p>
 *
 * @author yuguoyao
 * @since 2022-06-05
 */
@Getter
@Setter
@TableName("doctor_intro")
@ApiModel(value = "DoctorIntro对象", description = "医生信息介绍表")
public class DoctorIntro implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("医生编号")
    private String docId;

    @ApiModelProperty("医生姓名")
    private String docName;

    @ApiModelProperty("性别")
    private String sex;

    @ApiModelProperty("年龄")
    private Integer age;

    @ApiModelProperty("所在科室")
    private String departmantName;

    @ApiModelProperty("详细介绍")
    private String docDetail;


}
