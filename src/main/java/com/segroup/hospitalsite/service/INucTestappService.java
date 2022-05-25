package com.segroup.hospitalsite.service;

import com.segroup.hospitalsite.entity.NucTestapp;
import com.baomidou.mybatisplus.extension.service.IService;
import com.segroup.hospitalsite.mapper.NucTestappMapper;
import org.springframework.beans.factory.annotation.Autowired;

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
