package com.segroup.hospitalsite.UserInfo.mapper;

import com.segroup.hospitalsite.UserInfo.entity.UserInfoEntity;
import com.segroup.hospitalsite.mapper.UserInfoMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest // 表示标注当前类为标注类，不会随项目打包
@RunWith(SpringRunner.class) // 表示启动这个单元测试类
public class UserInfoMapperTests {
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Test
    /**
     * 可以单独运行，不用启动整个项目
     * 返回值为void, 参数列表无类型，不需是public
     */
    public void insert(){
        UserInfoEntity userInfoEntity = new UserInfoEntity();
        userInfoEntity.setId("testid");
        userInfoEntity.setName("testname");
        userInfoEntity.setPassword("testpwd");
        userInfoEntity.setPhone("test110");
        userInfoEntity.setIsAdmin(1);
        Integer rows = userInfoMapper.insertNew(userInfoEntity);
        System.out.println(rows);
    }
    @Test
    public void find(){
        UserInfoEntity userInfoEntity = userInfoMapper.findByUid(1);
        System.out.println("findByUid: " + userInfoEntity);
        System.out.println("findById: " + userInfoMapper.findById("3390052222222222"));
        System.out.println("findByNumber: " + userInfoMapper.findByPhone("163666666"));
    }
    @Test
    public void update(){
        UserInfoEntity userInfoEntity = userInfoMapper.findById("testid");
        userInfoEntity.setId("testid1");
        userInfoEntity.setName("altered");
        userInfoEntity.setPassword("altered");
        userInfoEntity.setPhone("altered");
        userInfoEntity.setIsAdmin(0);
        userInfoMapper.updateByUid(userInfoEntity);
    }
}
