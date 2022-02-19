package com.landao.web.plus.core;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.ResourceLoader;

/**
 * 控制器日志切面
 */
@Aspect
public class RequestLogAspect{


    @Before("execution(public * *..controller.*Controller.*(..))")
    public void startLog(JoinPoint joinPoint) {
        RequestLogHolder.clear();
        RequestLogHolder.startLog(joinPoint);
    }

}
