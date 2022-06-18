package com.segroup.hospitalsite.NGP.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.sql.Timestamp;
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
 * @author Liu Yibin
 * @since 2022-5-26
 */
@Getter
@Setter
@ApiModel(value = "Notice对象", description = "")
public class Notice implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
    private Long id;

    private String title;

    private Integer authorId;

    @TableField(value = "release_time", fill = FieldFill.INSERT)
    private Timestamp releaseTime;
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Timestamp updateTime;

    @Version
    private Integer version;

    @TableLogic
    private Integer deleted;

    private String content;


}
