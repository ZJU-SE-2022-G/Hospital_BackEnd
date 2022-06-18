package com.segroup.hospitalsite.VacApp.service.impl;

import com.segroup.hospitalsite.VacApp.entity.VacApp;
import com.segroup.hospitalsite.mapper.VacAppMapper;
import com.segroup.hospitalsite.VacApp.service.IVacAppService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 疫苗预约接种表 服务实现类
 * </p>
 *
 * @author Tristan
 * @since 2022-05-26
 */
@Service
public class VacAppServiceImpl extends ServiceImpl<VacAppMapper, VacApp> implements IVacAppService {

    @Autowired
    private VacAppMapper vcMapper;

    @Override
    public void updateByUsrId(String usr_id, String new_date){
        vcMapper.updateByUsrId(usr_id, new_date);
    }
}
