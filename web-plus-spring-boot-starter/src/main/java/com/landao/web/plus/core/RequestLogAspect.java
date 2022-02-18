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


    @Before("@within(org.springframework.web.bind.annotation.RestController) "
            + or + "@within(org.springframework.stereotype.Controller)"
            + or + "@within(com.landao.web.plus.annotation.RequestController)")
    public void startLog(JoinPoint joinPoint) {
        RequestLogHolder.clear();
        RequestLogHolder.startLog(joinPoint);
    }

}
