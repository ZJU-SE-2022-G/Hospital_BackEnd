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
 * @since 2022-06-12
 */
@Getter
@Setter
@ApiModel(value = "Workday对象", description = "")
public class Workday implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "wid", type = IdType.AUTO)
    private Integer wid;

    @ApiModelProperty("医生id")
    private String did;

    @ApiModelProperty("医生工作时间")
    private String workTime;

    @ApiModelProperty("医生这天的号源数")
    private Integer totalNum;

    @ApiModelProperty("已预约人数")
    private Integer orderedNum;

    @ApiModelProperty("状态：已满，预约，停诊")
    private String state;

    public int decrement() {
        if(orderedNum == 0)
        {
            return -1;
        }
        else {
            orderedNum--;
            if("已满".equals(state))
            {
                state = "可被预约的";
            }
            return orderedNum;
        }
    }
    public int increment() {
        if("已满".equals(state))
        {
            return -1;
        }
        else {
            orderedNum++;
            if(orderedNum.equals(totalNum))
            {
                state = "已满";
            }
            return orderedNum;
        }
    }
}
