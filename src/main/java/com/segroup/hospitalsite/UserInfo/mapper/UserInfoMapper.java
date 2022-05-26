package com.segroup.hospitalsite.UserInfo.mapper;

import com.segroup.hospitalsite.UserInfo.entity.UserInfoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository // 数据库相关
public interface UserInfoMapper {
    /**
     * 插入一条新记录，用于注册
     * @param userInfoEntity 用户数据
     * @return 受影响的行数
     */
    public Integer insertNew(UserInfoEntity userInfoEntity);

    /**
     * 使用数字uid查询用户记录,在个人信息修改当中使用
     * @param uid
     * @return UserInfoEntity, 如果没找到则null
     */
    public UserInfoEntity findByUid(Integer uid);

    /**
     * 用于使用身份证登录
     * @param id 身份证号码
     * @return  用户信息, 如果没找到则null
     */
    public UserInfoEntity findById(String id);

    /**
     * 用于使用手机号登录
     * @param phone 身份证号码
     * @return  用户信息, 如果没找到则null
     */
    public UserInfoEntity findByPhone(String phone);

    /**
     * 用于修改用户信息
     * @param userInfoEntity 使用uid来确定对象，允许修改id, password, name, phone, isAdmin
     * @return 受影响的行数
     */
    public Integer updateByUid(UserInfoEntity userInfoEntity);
}
