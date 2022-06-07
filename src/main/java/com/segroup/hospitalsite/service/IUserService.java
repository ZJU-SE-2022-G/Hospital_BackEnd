package com.segroup.hospitalsite.service;

import com.segroup.hospitalsite.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zheng
 * @since 2022-03-30
 */
public interface IUserService extends IService<User> {
    public int deleteUser(Long id);
}
