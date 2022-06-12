package com.segroup.hospitalsite.NGP.util;

import java.io.Serializable;
import java.util.Collection;

public class JsonResultUtil<E> implements Serializable {
    /*状态码
     * 1：登陆成功
     * 2：用户名不存在
     * 3：用户名正确密码错误
     * 4：其他异常
     * */
    private Integer state;
    /*状态描述信息*/
    private String message;
    /*响应数据*/
    private Object data;

    public JsonResultUtil(Integer state) {
        this.state = state;
    }

    public JsonResultUtil() {
    }

    public JsonResultUtil(Throwable e) {
        this.message = e.getMessage();
    }

    public JsonResultUtil(String message) {
        this.message = message;
    }

    public JsonResultUtil(Object data) {
        this.data = data;
    }

    public JsonResultUtil(Integer state, Object data) {
        this.state = state;
        this.data = data;
    }

    public JsonResultUtil(Integer state, String message) {
        this.state = state;
        this.message = message;
    }


    public JsonResultUtil(Integer state, String message, Object data) {
        this.state = state;
        this.message = message;
        this.data = data;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}