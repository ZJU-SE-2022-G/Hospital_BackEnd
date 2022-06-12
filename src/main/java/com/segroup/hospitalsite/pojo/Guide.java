package com.segroup.hospitalsite.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
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
@ApiModel(value = "Guide对象", description = "")
public class Guide implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String title;

    private Integer authorId;

    @TableField(fill = FieldFill.INSERT)
    private Timestamp releaseTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Timestamp updateTime;

    @Version
    private Integer version;

    @TableLogic
    private Integer deleted;

    private String content;


}
