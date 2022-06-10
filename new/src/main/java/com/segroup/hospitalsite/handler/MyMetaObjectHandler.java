package com.segroup.hospitalsite.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

import java.sql.Time;
import java.sql.Timestamp;

public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        Timestamp T = new Timestamp(System.currentTimeMillis());
        this.setFieldValByName("releaseTime", T,metaObject);
        this.setFieldValByName("updateTime", T,metaObject);
        this.setFieldValByName("askTime", T,metaObject);
        this.setFieldValByName("resTime", T,metaObject);

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Timestamp T = new Timestamp(System.currentTimeMillis());
        this.setFieldValByName("updateTime", T,metaObject);
        this.setFieldValByName("resTime", T,metaObject);
    }
}
