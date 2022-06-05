package com.segroup.hospitalsite.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@ApiModel(value = "名医对象", description = "")
public class DoctorIntroduction implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("名医姓名")
    private String name;

    @ApiModelProperty("名医详情")
    private String introduction;

}
