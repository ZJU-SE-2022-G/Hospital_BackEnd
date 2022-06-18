package com.segroup.hospitalsite.IntroductionApp.entity;

import com.baomidou.mybatisplus.annotation.TableId;
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

    public DoctorIntro(String doc_id, String doc_name, String doc_sex, Integer doc_age, String department_name, String detail)
    {
        docId = doc_id;
        docName = doc_name;
        sex = doc_sex;
        age = doc_age;
        departmantName = department_name;
        docDetail = detail;
    }

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("医生编号")
    @TableId()
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
