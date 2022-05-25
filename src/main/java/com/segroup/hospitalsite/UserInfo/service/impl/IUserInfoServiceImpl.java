package com.segroup.hospitalsite.UserInfo.service.impl;

import com.segroup.hospitalsite.UserInfo.entity.UserInfoEntity;
import com.segroup.hospitalsite.UserInfo.mapper.UserInfoMapper;
import com.segroup.hospitalsite.UserInfo.service.IUserInfoService;
import com.segroup.hospitalsite.UserInfo.service.exception.*;
import com.segroup.hospitalsite.UserInfo.service.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.UUID;


// 这个类响应controller下来的find函数
@Service
public class IUserInfoServiceImpl implements IUserInfoService {
    final Boolean useMD5 = Boolean.FALSE;
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public void register(UserInfoEntity user){
        if(useMD5){
            // MD5加密处理
            String realPwd = user.getPassword();
            // 随机生成盐值
            String salt = UUID.randomUUID().toString().toUpperCase();
            String md5Password = encryptMD5(realPwd, salt);
            user.setPassword(md5Password);
            user.setSalt(salt);
        }
        // 默认为非管理员
        if(user.getIsAdmin() == null)
            user.setIsAdmin(0);

        // 先检测phone和id是否被占用
        UserInfoEntity phone_ptr = userInfoMapper.findByPhone(user.getPhone());
        UserInfoEntity id_ptr = userInfoMapper.findById(user.getId());
        if(phone_ptr != null)
            throw new PhoneDuplicationException("该手机号已注册");
        if(id_ptr != null)
            throw new IdDuplicationException("该身份证号已注册");
        Integer rows = userInfoMapper.insertNew(user);
        if(rows != 1)
            throw new InsertionException("注册时产生了未知异常");
    }

    @Override
    public UserInfoEntity login(String id, String password){
        UserInfoEntity result = userInfoMapper.findById(id);
        // 先获取用户信息
        if(result == null){
            throw new IdNotFoundException("该用户id不存在！");
        }
        String realPwd = "";
        // 检测密码是否匹配
        if(useMD5){
            realPwd = encryptMD5(password, result.getSalt());
        }
        else
            realPwd = password;

        if(!realPwd.equals(result.getPassword()))
            throw new PasswordNotMatchException("密码错误");
        result.setSalt(""); // 为了安全和性能考虑，删除盐值
        return result;
    }

    @Override
    public UserInfoEntity loginByPhone(String phone, String password){
        UserInfoEntity result = userInfoMapper.findByPhone(phone);
        // 先获取用户信息
        if(result == null){
            throw new PhoneNotFoundException("该手机号不存在！");
        }
        String realPwd = "";
        // 检测密码是否匹配
        if(useMD5){
            realPwd = encryptMD5(password, result.getSalt());
        }
        else
            realPwd = password;

        if(!realPwd.equals(result.getPassword()))
            throw new PasswordNotMatchException("密码错误");
        result.setSalt(""); // 为了安全和性能考虑，删除盐值
        return result;
    }

    @Override
    public void update(UserInfoEntity user){
        UserInfoEntity result = userInfoMapper.findByUid(user.getUid());
        if (result == null)
            throw new UidNotFoundException("当前用户对应的UID不存在");
        if(user.getIsAdmin() == null)
            user.setIsAdmin(0);

        if(user.getId() == null)
            throw new NullException("用户名不可为空，前端应保证用户名称符合正确的格式");

        // 如果选择使用md5加密，则使用result的盐值重新加密
        if(useMD5)
            user.setPassword(encryptMD5(user.getPassword(), result.getSalt()));
        // 如果修改了用户名，则检查用户名是否被占用
        if( !result.getId().equals(user.getId())){
            UserInfoEntity temp = userInfoMapper.findById(user.getId());
            if(temp != null)
                throw new IdDuplicationException("该用户名已被占用，请尝试其他的名称");
        }

        // 如果修改了手机号，则检查手机号是否被占用
        if(result.getPhone() != null & user.getPhone()!=null){
            if(!result.getPhone().equals(user.getPhone())){
                UserInfoEntity temp = userInfoMapper.findByPhone(user.getPhone());
                if(temp != null)
                    throw new PhoneDuplicationException("该手机号已经绑定到其他账号上了");
            }
        }

        Integer rows = userInfoMapper.updateByUid(user);
        if(rows != 1)
            throw new UpdateException("注册时产生了未知异常");
    }

    /**
     * 使用盐值进行加密
     * @param pwd
     * @param salt
     * @return 加密后的md5字符串
     */
    private String encryptMD5(String pwd, final String salt){
        for(int i=0; i<3; i++)
            pwd = DigestUtils.md5DigestAsHex((salt+pwd+salt)
                    .getBytes(StandardCharsets.UTF_8)).toUpperCase();
        return pwd;
    }


}
