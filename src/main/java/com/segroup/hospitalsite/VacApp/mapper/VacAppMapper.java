package com.segroup.hospitalsite.VacApp.mapper;

import com.segroup.hospitalsite.VacApp.entity.VacApp;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 疫苗预约接种表 Mapper 接口
 * </p>
 *
 * @author Tristan
 * @since 2022-05-26
 */
@Mapper
public interface VacAppMapper extends BaseMapper<VacApp> {
    void updateByUsrId(String usr_id, String new_date);
}
