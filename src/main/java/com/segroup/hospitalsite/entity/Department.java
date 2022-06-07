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
@ApiModel(value = "Department对象", description = "")
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("科室号")
    @TableId(value = "mid", type = IdType.AUTO)
    private Integer mid;

    @ApiModelProperty("科室名")
    private String mname;

    @ApiModelProperty("科室地址")
    private String room;

    @ApiModelProperty("科室电话")
    private String phone;

    @ApiModelProperty("科室介绍")
    private String description;

    private Integer doctorNum;


}
