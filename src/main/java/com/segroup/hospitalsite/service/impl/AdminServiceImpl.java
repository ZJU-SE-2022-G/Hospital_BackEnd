package com.segroup.hospitalsite.service.impl;

import com.segroup.hospitalsite.entity.Admin;
import com.segroup.hospitalsite.mapper.AdminMapper;
import com.segroup.hospitalsite.service.IAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

}
