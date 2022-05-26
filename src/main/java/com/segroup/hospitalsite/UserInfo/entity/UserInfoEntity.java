package com.segroup.hospitalsite.UserInfo.entity;


import lombok.Data;

import java.io.Serializable;

@Data
public class UserInfoEntity implements Serializable {
    private Integer uid;        // 这个键值是auto increase，对用户不可见，用于加速查询
    private String id;          // 身份证号，不许重复
    private String password;    // 密码
    private String name;        // 姓名
    private String phone;       // 手机号
    private Integer isAdmin;    // 是否为管理员
    private String salt;        // 盐值
}
