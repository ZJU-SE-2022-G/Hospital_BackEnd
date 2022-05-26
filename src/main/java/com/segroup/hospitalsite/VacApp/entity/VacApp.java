package com.segroup.hospitalsite.VacApp.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 疫苗预约接种表
 * </p>
 *
 * @author Tristan
 * @since 2022-05-26
 */
@Getter
@Setter
@TableName("vac_app")
@ApiModel(value = "VacApp对象", description = "疫苗预约接种表")
public class VacApp implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("身份证号")
    private String usrId;

    @ApiModelProperty("姓名")
    private String usrName;

    @ApiModelProperty("年龄")
    private Integer age;

    @ApiModelProperty("性别")
    private String sex;

    @ApiModelProperty("预约接种第几针疫苗")
    private Integer vacNum;

    @ApiModelProperty("疫苗预约时间")
    private LocalDate vacDate;


}
