package com.segroup.hospitalsite.NucTest.mapper;

import com.segroup.hospitalsite.NucTest.entity.NucTestApp;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;

/**
 * <p>
 * 核酸检测预约表 Mapper 接口
 * </p>
 *
 * @author Tristan
 * @since 2022-05-25
 */
@Mapper
public interface NucTestAppMapper extends BaseMapper<NucTestApp> {
    void updateByUsrId(String usr_id, LocalDate new_date);
}
