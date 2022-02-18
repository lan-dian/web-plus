package com.landao.web.plus.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.landao.web.plus.WebPlusManager;
import com.landao.web.plus.WebPlusProperties;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


public class DateTimeMetaObjectHandler implements MetaObjectHandler, InitializingBean {

    @Autowired
    private WebPlusProperties webPlusProperties;

    @Override
    public void afterPropertiesSet(){
        WebPlusProperties.MybatisPlus mybatisPlus = webPlusProperties.getMybatisPlus();
        createTime=mybatisPlus.getCreateTime();
        updateTime=mybatisPlus.getUpdateTime();
    }

    public String createTime;

    public String updateTime;

    /**
     * 插入时填充
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, createTime, LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject,updateTime,LocalDateTime.class,LocalDateTime.now());
    }

    /**
     * 更新时填充
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, updateTime, LocalDateTime.class, LocalDateTime.now());
    }

}
