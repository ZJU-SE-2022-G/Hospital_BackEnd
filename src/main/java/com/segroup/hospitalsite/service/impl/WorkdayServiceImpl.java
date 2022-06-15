package com.segroup.hospitalsite.service.impl;

import com.segroup.hospitalsite.entity.Workday;
import com.segroup.hospitalsite.mapper.WorkdayMapper;
import com.segroup.hospitalsite.service.IWorkdayService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zheng
 * @since 2022-06-12
 */
@Service
public class WorkdayServiceImpl extends ServiceImpl<WorkdayMapper, Workday> implements IWorkdayService {

}
