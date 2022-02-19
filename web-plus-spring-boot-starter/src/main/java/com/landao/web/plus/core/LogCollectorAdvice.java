package com.landao.web.plus.core;


import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.annotation.Resource;


@RestControllerAdvice
public class LogCollectorAdvice implements ResponseBodyAdvice<Object> {

    @Resource
    private RequestLogCollector requestLogCollector;

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if(body!=null){
            RequestLog requestLog = RequestLogHolder.endLog(body,body.getClass());
            if(requestLog!=null){
               requestLogCollector.collectRequestLog(requestLog);
            }
        }
        return body;
    }
}
