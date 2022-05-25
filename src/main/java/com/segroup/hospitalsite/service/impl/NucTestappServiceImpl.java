package com.segroup.hospitalsite.service.impl;

import com.segroup.hospitalsite.entity.NucTestapp;
import com.segroup.hospitalsite.mapper.NucTestappMapper;
import com.segroup.hospitalsite.service.INucTestappService;
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
public class NucTestappServiceImpl extends ServiceImpl<NucTestappMapper, NucTestapp> implements INucTestappService {
    @Autowired
    private NucTestappMapper ntaMapper;

    @Override
    public void updateByUsrId(String usr_id, LocalDate new_date) {
        ntaMapper.updateByUsrId(usr_id, new_date);
    }
}
