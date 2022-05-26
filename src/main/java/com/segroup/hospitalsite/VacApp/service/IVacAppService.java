package com.segroup.hospitalsite.VacApp.service;

import com.segroup.hospitalsite.VacApp.entity.VacApp;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 疫苗预约接种表 服务类
 * </p>
 *
 * @author Tristan
 * @since 2022-05-26
 */
public interface IVacAppService extends IService<VacApp> {

    void updateByUsrId(String usr_id, String new_date);
}
