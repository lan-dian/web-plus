package com.landao.web.plus;


import com.landao.web.plus.core.RequestLog;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class WebPlusManager implements ApplicationContextAware {

    public static ApplicationContext applicationContext;


    public static WebPlusProperties webPlusProperties;

    public static WebPlusProperties.DateFormat dateFormat;

    public static WebPlusProperties.MybatisPlus mybatisPlus;

    public static WebPlusProperties.Result result;


    @Autowired
    public void setWebPlusProperties(WebPlusProperties webPlusProperties){
        WebPlusManager.webPlusProperties=webPlusProperties;
        WebPlusManager.dateFormat=webPlusProperties.getDateFormat();
        WebPlusManager.mybatisPlus=webPlusProperties.getMybatisPlus();
        WebPlusManager.result=webPlusProperties.getResult();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        WebPlusManager.applicationContext=applicationContext;
    }

    public static RequestLog getRequestLog(){
        return applicationContext.getBean(RequestLog.class);
    }



}
