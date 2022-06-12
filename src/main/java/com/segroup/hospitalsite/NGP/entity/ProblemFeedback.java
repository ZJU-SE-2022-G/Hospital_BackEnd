package com.segroup.hospitalsite.NGP.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("problem_feedback")
@ApiModel(value = "ProblemFeedback对象", description = "")
public class ProblemFeedback implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String problemType;

    private String problem;

    private Integer askerId;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Timestamp askTime;

    private String answer;

    private Integer respondentId;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Timestamp resTime;

    @TableLogic
    private Integer deleted;

    @Version
    private Integer version;


}
