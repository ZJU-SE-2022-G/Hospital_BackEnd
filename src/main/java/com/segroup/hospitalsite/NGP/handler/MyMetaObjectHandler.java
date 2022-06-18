package com.segroup.hospitalsite.NGP.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;


import java.sql.Timestamp;
@Component
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
