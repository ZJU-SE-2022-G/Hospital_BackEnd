package com.segroup.hospitalsite.UserInfo.utils;


import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


/**
 * 使用Json响应
 */
public class JsonResult<E> implements Serializable {
    // 状态码
    private Integer state;
    // 描述信息
    private String message;
    // 数据
    private E data; // 使用泛型

    public void setState(Integer s){
        state = s;
    }

    public void setMessage(String msg){
        message = msg;
    }

    public Integer getState() {
        return state;
    }

    public String getMessage() {
        return message;
    }

    public void setData(E d){
        data = d;
    }

    public E getData() {
        return data;
    }

    public JsonResult() {
    }

    public JsonResult(Integer state) {
        this.state = state;
    }

    public JsonResult(Integer state, String msg){
        this.state = state;
        this.message = msg;
    }

    public JsonResult(Throwable e) {
        this.message = e.getMessage();
    }

    public JsonResult(String message) {
        this.message = message;
    }

    public JsonResult(String message, E data) {
        this.message = message;
        this.data = data;
    }

    public String returnJson(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("state", state);
        map.put("message", message);
        map.put("data", data);
        String json = JSON.toJSONString(map);
//        System.out.println(json);
        return json;
    }
}
