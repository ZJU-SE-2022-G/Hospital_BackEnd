package com.segroup.hospitalsite.service.impl;

import com.segroup.hospitalsite.entity.Integrity;
import com.segroup.hospitalsite.mapper.IntegrityMapper;
import com.segroup.hospitalsite.service.IIntegrityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zheng
 * @since 2022-05-26
 */
@Service
public class IntegrityServiceImpl extends ServiceImpl<IntegrityMapper, Integrity> implements IIntegrityService {

    @Override
    public void configTask(ScheduledTaskRegistrar scheduledTaskRegistrar) {
//        scheduledTaskRegistrar.addTriggerTask(
//                triggerContext->{
//                    Schedule
//                }
//        );
    }
}
