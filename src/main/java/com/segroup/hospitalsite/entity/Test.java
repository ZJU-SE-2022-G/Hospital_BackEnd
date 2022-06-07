package com.segroup.hospitalsite.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 测试表
 * </p>
 *
 * @author zheng
 * @since 2022-05-25
 */
@Getter
@Setter
@ApiModel(value = "Test对象", description = "测试表")
public class Test implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("年龄")
    private Integer age;

    @ApiModelProperty("删除标识1")
    private Boolean deleteFlag;

    @ApiModelProperty("删除标识2")
    private Boolean deleted;

    @ApiModelProperty("版本")
    private Long version;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;


}
