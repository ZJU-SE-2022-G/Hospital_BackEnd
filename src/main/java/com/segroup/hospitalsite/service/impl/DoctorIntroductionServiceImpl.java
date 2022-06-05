package com.segroup.hospitalsite.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.segroup.hospitalsite.entity.DoctorIntroduction;
import com.segroup.hospitalsite.mapper.DoctorIntroductionMapper;
import com.segroup.hospitalsite.service.IDoctorIntroductionService;
import org.springframework.stereotype.Service;

@Service
public class DoctorIntroductionServiceImpl
        extends ServiceImpl<DoctorIntroductionMapper, DoctorIntroduction>
        implements IDoctorIntroductionService
{
}
