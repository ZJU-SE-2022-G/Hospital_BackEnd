package com.segroup.hospitalsite.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;
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
@ApiModel(value = "Integrity对象", description = "")
public class Integrity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "iid", type = IdType.AUTO)
    private Integer iid;

    @ApiModelProperty("用户id")
    private Integer pid;

    @ApiModelProperty("分值")
    private Integer score;

    @ApiModelProperty("违约时间")
    private LocalDateTime time;


}
