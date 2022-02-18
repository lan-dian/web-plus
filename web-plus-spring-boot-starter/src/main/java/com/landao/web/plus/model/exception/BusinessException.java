package com.landao.web.plus.model.exception;

import com.landao.web.plus.model.response.ResultAble;
import com.landao.web.plus.WebPlusManager;

/**
 * 业务异常
 */
public class BusinessException extends RuntimeException implements ResultAble<Object> {

    private final Integer code;

    private final String msg;

    private final Object data;

    public static final Integer errorCode= WebPlusManager.result.getErrorCode();

    public BusinessException(String msg){
        this(errorCode,msg,null);
    }

    public BusinessException(String msg, Integer code){
        this(code,msg,null);
    }

    public BusinessException(Object data){
        this(errorCode,"失败",data);
    }

    public BusinessException(Object data,Integer code){
        this(code,"失败",data);
    }

    public BusinessException(Integer code,String msg,Object data){
        this.code=code;
        this.msg=msg;
        this.data=data;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public Object getData() {
        return data;
    }

}
