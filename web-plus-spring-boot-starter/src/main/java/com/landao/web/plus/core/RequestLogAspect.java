package com.landao.web.plus.core;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * 控制器日志切面
 */
@Aspect
public class RequestLogAspect {

    public static final String or = "||";


    @Before("execution(public * *..controller.*.*(..))")
    public void startLog(JoinPoint joinPoint) {
        RequestLogHolder.clear();
        RequestLogHolder.startLog(joinPoint);
    }

}
