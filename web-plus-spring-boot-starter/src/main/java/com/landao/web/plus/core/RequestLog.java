package com.landao.web.plus.core;

import org.aspectj.lang.JoinPoint;

public interface RequestLog {

    /**
     * 开始记录日志
     * @param point 控制器方法
     */
    void startLog(JoinPoint point);

    /**
     * 结束日志
     * @param result 控制器反回的结果
     */
    void endLog(Object result);

    /**
     * 进入控制器失败,初始化日志
     */
    void startLog();

}
