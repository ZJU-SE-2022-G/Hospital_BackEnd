package com.segroup.hospitalsite.IntroductionApp.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 科室信息介绍表
 * </p>
 *
 * @author yuguoyao
 * @since 2022-06-06
 */
@Getter
@Setter
@TableName("department_intro")
@ApiModel(value = "DepartmentIntro对象", description = "科室信息介绍表")
public class DepartmentIntro implements Serializable {

    public DepartmentIntro(String department_id, String department_name, String intro_detail)
    {
        id = department_id;
        name = department_name;
        detail = intro_detail;
    }

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("科室编号")
    private String id;

    @ApiModelProperty("科室名称")
    private String name;

    @ApiModelProperty("科室详情")
    private String detail;


}
