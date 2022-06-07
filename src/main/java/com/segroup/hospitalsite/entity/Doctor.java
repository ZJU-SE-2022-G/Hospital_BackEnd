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
@ApiModel(value = "Doctor对象", description = "")
public class Doctor implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("医生编号")
    @TableId(value = "did", type = IdType.AUTO)
    private Integer did;

    @ApiModelProperty("姓名")
    private String dname;

    @ApiModelProperty("性别")
    private String gender;

    @ApiModelProperty("年龄")
    private Integer age;

    @ApiModelProperty("职称")
    private String title;

    @ApiModelProperty("科室号")
    private Integer mid;

    @ApiModelProperty("介绍")
    private String description;


}
