package com.segroup.hospitalsite.UserInfo.controller;

import com.segroup.hospitalsite.UserInfo.entity.UserInfoEntity;
import com.segroup.hospitalsite.UserInfo.service.IUserInfoService;
import com.segroup.hospitalsite.UserInfo.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/users")
public class UserInfoController extends UserInfoBaseController {
    @Autowired
    private IUserInfoService iUserInfoService;

    // 参数列表为pojo类型
    @RequestMapping("/reg")
    public String register(UserInfoEntity userInfoEntity){
        System.out.println("[Controller Output]" + userInfoEntity);
        iUserInfoService.register(userInfoEntity);
        JsonResult<Void> result = new JsonResult<>(SUCCESS, "注册成功");

        String json = result.returnJson();
        System.out.println("[Controller Output]" + json);
        return json;
    }

    @RequestMapping("/login")
    public String login(String id, String password, HttpSession session){
        UserInfoEntity userInfoEntity = iUserInfoService.login(id, password);
        JsonResult<UserInfoEntity> result = new JsonResult<UserInfoEntity>(SUCCESS, "登录成功");

        // 设置session
        session.setAttribute("uid", userInfoEntity.getUid());
        session.setAttribute("id", userInfoEntity.getId());
        session.setAttribute("name", userInfoEntity.getName());
        session.setAttribute("phone", userInfoEntity.getPhone());
        session.setAttribute("isAdmin", userInfoEntity.getIsAdmin());

        System.out.println(getIdFromSession(session));
        System.out.println(getIsAdminFromSession(session));
        System.out.println(getNameFromSession(session));
        System.out.println(getPhoneFromSession(session));
        System.out.println(getUidFromSession(session));
        result.setData(userInfoEntity);
        String json = result.returnJson();
        System.out.println("[Controller Output]" + json);
        return json;
    }


    @RequestMapping("/loginByPhone")
    public String loginByPhone(String phone, String password){
        UserInfoEntity userInfoEntity = iUserInfoService.loginByPhone(phone, password);
        JsonResult<UserInfoEntity> result = new JsonResult<UserInfoEntity>(SUCCESS, "登录成功");

        result.setData(userInfoEntity);
        String json = result.returnJson();
        System.out.println("[Controller Output]" + json);
//        JSONObject json = JSONObject.parseObject(result.returnJson());
//        String data = json.getString("data");
//        JSONObject djson = JSONObject.parseObject(data);
//        System.out.println(djson.getString("password"));
        return json;
    }

    @RequestMapping("/update")
    public String update(UserInfoEntity userInfoEntity){
        System.out.println("[Controller Output]" + userInfoEntity);
        iUserInfoService.update(userInfoEntity);
        JsonResult<UserInfoEntity> result = new JsonResult<UserInfoEntity>(SUCCESS, "更新成功");

        result.setData(userInfoEntity);
        String json = result.returnJson();
        System.out.println("[Controller Output]" + json);
        return json;
    }
}
