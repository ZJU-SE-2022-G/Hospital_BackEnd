package com.segroup.hospitalsite.service;

import com.segroup.hospitalsite.entity.Integrity;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zheng
 * @since 2022-05-26
 */
public interface IIntegrityService extends IService<Integrity> {
    public void configTask(ScheduledTaskRegistrar scheduledTaskRegistrar);
}
