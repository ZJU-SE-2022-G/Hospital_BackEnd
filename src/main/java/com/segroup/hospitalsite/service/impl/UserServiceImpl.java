package com.segroup.hospitalsite.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.segroup.hospitalsite.entity.User;
import com.segroup.hospitalsite.mapper.UserMapper;
import com.segroup.hospitalsite.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zheng
 * @since 2022-03-30
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private UserMapper userMapper;

    @Override
    public int deleteUser(Long id)
    {
        return userMapper.deleteById(id);
    }
}
