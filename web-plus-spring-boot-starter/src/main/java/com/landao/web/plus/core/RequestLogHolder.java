package com.landao.web.plus.core;

import com.landao.web.plus.WebPlusManager;
import org.aspectj.lang.JoinPoint;

public class RequestLogHolder {

    private RequestLogHolder(){
    }

    public static final ThreadLocal<RequestLog> REQUEST_LOG = new ThreadLocal<>();

    public static void clear() {
        REQUEST_LOG.remove();
    }

    public static void startLog(JoinPoint joinPoint){
        RequestLog requestLog = WebPlusManager.getRequestLog();
        REQUEST_LOG.set(requestLog);
        requestLog.startLog(joinPoint);
    }


    public static RequestLog endLog(Object result,Class<?> resultType){
        RequestLog requestLog=REQUEST_LOG.get();
        if(requestLog==null){
            requestLog = WebPlusManager.getRequestLog();
            requestLog.startLog();
        }
        if(requestLog.support(resultType)){
            requestLog.endLog(result);
            return requestLog;
        }
        return null;
    }



}
