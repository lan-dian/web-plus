package com.landao.web.plus.model.response;

import com.landao.web.plus.WebPlusManager;

/**
 * 统一返回接口包装类
 * @param <T>
 */
public class CommonResult<T> {

    private Integer code;

    private String msg;

    private T data;

    public static final Integer successCode= WebPlusManager.result.getSuccessCode();

    public static final Integer errorCode= WebPlusManager.result.getErrorCode();

    public CommonResult() {
        this.code = successCode;
        this.msg = "成功";
    }

    public CommonResult(Integer code, String msg, T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public CommonResult(ResultAble<T> resultAble){
        this.code=resultAble.getCode();
        this.msg=resultAble.getMsg();
        this.data=resultAble.getData();
    }

    public static CommonResult<Void> build(){
        return new CommonResult<>();
    }

    /**
     * 当service返回boolean
     */
    public static CommonResult<Void> ok(boolean success){
        return ok(success,"失败");
    }

    /**
     * 当service返回boolean,如果success==false,显示msg
     */
    public static CommonResult<Void> ok(boolean success,String msg){
        CommonResult<Void> result = build();
        result.code=success?successCode:errorCode;
        result.msg=success?"成功":msg;
        return result;
    }

    /**
     *  当service返回void,如果中途没有异常直接返回ok
     */
    public static CommonResult<Void> ok(){
        CommonResult<Void> result = build();
        result.code=successCode;
        result.msg="成功";
        return result;
    }

    /**
     *  参数校验失败
     */
    public static CommonResult<Void> err(String msg){
        return err(msg,errorCode);
    }

    /**
     * 发生错误,需要特殊处理
     */
    public static CommonResult<Void> err(Integer code){
        return err("失败",code);
    }

    /**
     * 发生错误,需要特殊处理
     */
    public static CommonResult<Void> err(String msg,Integer code){
        CommonResult<Void> result = build();
        result.code=code;
        result.msg=msg;
        return result;
    }

    public CommonResult<T> body(T data){
        return body(data,successCode,"成功");
    }

    public CommonResult<T> success(){
        return body(null,successCode,"成功");
    }

    /**
     *  解析出现异常,需要处理异常数据
     */
    public CommonResult<T> err(T data){
        return body(data,errorCode,"异常");
    }

    /**
     *  解析出现异常,需要处理异常数据
     */
    public CommonResult<T> err(T data,Integer code){
        return body(data,code,"异常");
    }

    public CommonResult<T> body(T data,Integer code,String msg){
        this.code=code;
        this.msg=msg;
        this.data=data;
        return this;
    }

    /**
     *  参数校验失败
     */
    public  CommonResult<T> error(String msg){
        return error(msg,errorCode);
    }

    /**
     * 发生错误,需要特殊处理
     */
    public  CommonResult<T> error(Integer code){
        return error("失败",code);
    }

    /**
     * 发生错误,需要特殊处理
     */
    public  CommonResult<T> error(String msg,Integer code){
        this.code=code;
        this.msg=msg;
        return this;
    }

    public static CommonResult<Object> ok(ResultAble<Object> resultAble){
        CommonResult<Object> result=new CommonResult<>();
        result.code=resultAble.getCode();
        result.msg=resultAble.getMsg();
        result.data=resultAble.getData();
        return result;
    }

    public CommonResult<T> body(ResultAble<T> resultAble){
        this.code=resultAble.getCode();
        this.msg=resultAble.getMsg();
        this.data=resultAble.getData();
        return this;
    }



    //get 和 set 方法


    public Integer getCode() {
        return code;
    }

    public CommonResult<T> setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public CommonResult<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getData() {
        return data;
    }

    public CommonResult<T> setData(T data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return "CommonResult{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
