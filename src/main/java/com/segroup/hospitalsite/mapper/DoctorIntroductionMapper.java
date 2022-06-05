package com.segroup.hospitalsite.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.segroup.hospitalsite.entity.DoctorIntroduction;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DoctorIntroductionMapper extends BaseMapper<DoctorIntroduction> {
    void insertDoctorIntroMapper(String doctor_name, String doctor_intro);
}
