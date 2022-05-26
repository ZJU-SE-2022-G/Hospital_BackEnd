package com.segroup.hospitalsite.UserInfo.service;

import com.segroup.hospitalsite.UserInfo.entity.UserInfoEntity;
import com.segroup.hospitalsite.UserInfo.service.exception.UserInfoBaseException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest // 表示标注当前类为标注类，不会随项目打包
@RunWith(SpringRunner.class) // 表示启动这个单元测试类
public class UserInfoServiceTests {
    @Autowired
    private IUserInfoService iUserInfoService;
    @Test
    /**
     * 可以单独运行，不用启动整个项目
     * 返回值为void, 参数列表无类型，不需是public
     */
    public void register(){
        try {
            UserInfoEntity userInfoEntity = new UserInfoEntity();
            userInfoEntity.setId("testid1");
            userInfoEntity.setName("testname");
            userInfoEntity.setPassword("testpwd");
            userInfoEntity.setPhone("testphone1");
            userInfoEntity.setIsAdmin(1);
            iUserInfoService.register(userInfoEntity);
        } catch (UserInfoBaseException e) {
            // 获取异常的类对象，获取名称
            System.out.println(e.getClass().getSimpleName());
            // 获取异常的具体信息
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void login(){
        try{
            iUserInfoService.login("33666333156464", "user1");
        }catch (UserInfoBaseException e){
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void loginByPhone(){
        try {
            iUserInfoService.loginByPhone("55555555555", "user1");
        }catch (UserInfoBaseException e){
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

}
