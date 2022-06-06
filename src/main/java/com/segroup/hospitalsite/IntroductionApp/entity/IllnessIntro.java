package com.segroup.hospitalsite.IntroductionApp.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import com.segroup.hospitalsite.IntroductionApp.controller.IllnessIntroController;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 病情信息介绍表
 * </p>
 *
 * @author yuguoyao
 * @since 2022-06-06
 */
@Getter
@Setter
@TableName("illness_intro")
@ApiModel(value = "IllnessIntro对象", description = "病情信息介绍表")
public class IllnessIntro implements Serializable {

    public IllnessIntro(String illness_id, String illness_name, String intro_detail)
    {
        id = illness_id;
        name = illness_name;
        detail = intro_detail;
    }

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("病情编号")
    private String id;

    @ApiModelProperty("病情名称")
    private String name;

    @ApiModelProperty("病情简介")
    private String detail;


}
