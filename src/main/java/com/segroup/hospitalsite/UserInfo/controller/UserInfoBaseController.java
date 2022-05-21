package com.segroup.hospitalsite.UserInfo.controller;

import com.segroup.hospitalsite.UserInfo.service.exception.*;
import com.segroup.hospitalsite.UserInfo.utils.JsonResult;
import com.segroup.hospitalsite.UserInfo.service.exception.*;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

/** 控制层基类 */
public class UserInfoBaseController {
    // 成功的状态
    public static final int SUCCESS = 200;
    public static final int ID_DUPLICATION_ERROR = 4000;
    public static final int PHONE_NUMBER_DUPLICATION_ERROR = 4001;
    public static final int ID_NOT_FOUND_ERROR = 4002;
    public static final int PHONE_NOT_FOUND_ERROR = 4003;
    public static final int PASSWORD_NOT_MATCH_ERROR = 4004;
    public static final int UID_NOT_FOUND_ERROR = 4005;
    public static final int NULL_ERROR = 4006;
    public static final int INSERTION_ERROR = 5000;
    public static final int UPDATE_ERROR = 5001;

    // 统一处理抛出的异常
    // 请求处理方法，将返回的值给前端
    // 自动将异常对象传递给此方法的参数列表上
    // 当项目当中产生了异常，会被统一拦截到此方法当中
    @ExceptionHandler(UserInfoBaseException.class)
    public String handleException(Throwable e){
        JsonResult<Void> result = new JsonResult<>(e);
        if(e instanceof IdDuplicationException){
            result.setState(ID_DUPLICATION_ERROR);
            result.setMessage(e.getMessage());
        }
        else if(e instanceof PhoneDuplicationException){
            result.setState(PHONE_NUMBER_DUPLICATION_ERROR);
            result.setMessage(e.getMessage());
        }
        else if(e instanceof InsertionException){
            result.setState(INSERTION_ERROR);
            result.setMessage(e.getMessage());
        }
        else if(e instanceof PasswordNotMatchException){
            result.setState(PASSWORD_NOT_MATCH_ERROR);
            result.setMessage(e.getMessage());
        }
        else if(e instanceof PhoneNotFoundException){
            result.setState(PHONE_NOT_FOUND_ERROR);
            result.setMessage(e.getMessage());
        }
        else if(e instanceof IdNotFoundException){
            result.setState(ID_NOT_FOUND_ERROR);
            result.setMessage(e.getMessage());
        }
        else if(e instanceof UidNotFoundException){
            result.setState(UID_NOT_FOUND_ERROR);
            result.setMessage(e.getMessage());
        }
        else if(e instanceof UpdateException){
            result.setState(UPDATE_ERROR);
            result.setMessage(e.getMessage());
        }
        else if(e instanceof NullException){
            result.setState(NULL_ERROR);
            result.setMessage(e.getMessage());
        }

        String json = result.returnJson();
        System.out.println("[Controller Output]" + json);
        return json;
    }


    /**
     * 获取session中的uid
     * @param session session对象
     * @return 当前登录的用户uid值
     */
    protected final Integer getUidFromSession(HttpSession session){
        return Integer.valueOf(session.getAttribute("uid").toString());
    }

    /**
     * 获取当前登录用户的name
     * @param session session对象
     * @return 当前登录的用户name值
     */
    protected final String getNameFromSession(HttpSession session){
        if(session.getAttribute("name")!=null)
            return session.getAttribute("name").toString();
        else
            return null;
    }

    /**
     * 获取当前登录用户的手机号码
     * @param session session对象
     * @return 当前登录的用户phone值
     */
    protected final String getPhoneFromSession(HttpSession session){
        if(session.getAttribute("phone")!=null)
            return session.getAttribute("phone").toString();
        else
            return null;
    }

    /**
     * 获取当前登录用户的id
     * @param session session对象
     * @return 当前登录的用户id值
     */
    protected final String getIdFromSession(HttpSession session){
        if(session.getAttribute("id")!=null)
            return session.getAttribute("id").toString();
        else
            return null;
    }

    /**
     * 获取当前用户的权限
     * @param session session对象
     * @return True为admin，false不是
     */
    protected final Boolean getIsAdminFromSession(HttpSession session){
        if(session.getAttribute("isAdmin")!=null)
            return session.getAttribute("isAdmin").toString().equals("1");
        else
            return Boolean.FALSE;
    }

}
