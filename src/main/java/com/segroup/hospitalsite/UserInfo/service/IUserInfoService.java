package com.segroup.hospitalsite.UserInfo.service;

import com.segroup.hospitalsite.UserInfo.entity.UserInfoEntity;

public interface IUserInfoService {
    /**
     * 注册用户
     * @param user 用户数据对象
     */
    public void register(UserInfoEntity user);

    /**
     * 使用id登录
     * @param id 登录用户的身份证号
     * @param password
     * @return 匹配的用户数据，如果没有用户则null
     */
    public UserInfoEntity login(String id, String password);

    /**
     * 使用手机号登录
     * @param phone 登录用户的手机号
     * @param password
     * @return 匹配的用户数据，如果没有用户则null
     */
    public UserInfoEntity loginByPhone(String phone, String password);

    /**
     * 更新用户信息，只允许更新password，phone，name，isAdmin，同时要填入uid信息
     * @param user
     */
    public void update(UserInfoEntity user);
}
