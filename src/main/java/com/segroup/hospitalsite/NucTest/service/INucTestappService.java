package com.segroup.hospitalsite.NucTest.service;

import com.segroup.hospitalsite.NucTest.entity.NucTestapp;
import com.baomidou.mybatisplus.extension.service.IService;

import java.time.LocalDate;

/**
 * <p>
 * 核酸检测预约表 服务类
 * </p>
 *
 * @author Tristan
 * @since 2022-05-25
 */
public interface INucTestappService extends IService<NucTestapp> {
    void updateByUsrId(String usr_id, LocalDate new_date);
}
