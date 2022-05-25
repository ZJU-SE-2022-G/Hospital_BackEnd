package com.segroup.hospitalsite.NucTest.service.impl;

import com.segroup.hospitalsite.NucTest.service.INucTestappService;
import com.segroup.hospitalsite.NucTest.entity.NucTestApp;
import com.segroup.hospitalsite.NucTest.mapper.NucTestAppMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * <p>
 * 核酸检测预约表 服务实现类
 * </p>
 *
 * @author Tristan
 * @since 2022-05-25
 */
@Service
public class NucTestappServiceImpl extends ServiceImpl<NucTestAppMapper, NucTestApp> implements INucTestappService {
    @Autowired
    private NucTestAppMapper ntaMapper;

    @Override
    public void updateByUsrId(String usr_id, LocalDate new_date) {
        ntaMapper.updateByUsrId(usr_id, new_date);
    }
}
