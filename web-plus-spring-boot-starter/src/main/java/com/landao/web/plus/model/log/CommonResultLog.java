package com.landao.web.plus.model.log;

import com.landao.guardian.core.GuardianContext;
import com.landao.guardian.util.NewxWebUtils;
import com.landao.web.plus.core.RequestLog;
import com.landao.web.plus.model.response.CommonResult;
import com.landao.web.plus.utils.IpUtils;
import com.landao.web.plus.utils.JsonUtils;
import com.landao.web.plus.WebPlusManager;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Objects;
import java.util.StringJoiner;

public class CommonResultLog implements RequestLog {

    private LocalDateTime createTime;

    private Integer timeCost;

    private Object user;

    private String userType;

    /**
     * 请求url
     */
    private String url;

    /**
     * 来自ip
     */
    private String ip;

    /**
     * 方法签名
     */
    private String signature;

    /**
     * 请求参数
     */
    private String args;

    /**
     * 返回数据
     */
    private String data;

    /**
     * 返回状态码
     */
    private Integer code;

    /**
     * 返回消息
     */
    private String msg;

    /**
     * 返回的http状态码
     */
    private Integer status;

    /**
     * 是否成功
     */
    private Boolean success;

    @Override
    public void startLog(JoinPoint point) {
        initRequestLog();
        //设置类名和方法名
        Signature signature = point.getSignature();
        this.signature = signature.getDeclaringType().getSimpleName() + "#" + signature.getName();
        //设置参数
        StringJoiner arg = new StringJoiner("\t");
        MethodSignature methodSignature = (MethodSignature) signature;
        String[] parameterNames = methodSignature.getParameterNames();
        Object[] args = point.getArgs();
        for (int i = 0; i < parameterNames.length; i++) {
            String parameterName = parameterNames[i];
            arg.add(parameterName + "=" + JsonUtils.parse(args[i]));
        }
        this.args = arg.toString();
    }

    @Override
    public void startLog() {
        initRequestLog();
        //设置类名和方法名
        this.signature = "目标方法未执行";
        //设置参数
        this.args = "目标方法未执行";
    }

    /**
     * 初始化基本请求信息
     */
    private void initRequestLog() {
        this.createTime = LocalDateTime.now();
        HttpServletRequest request = NewxWebUtils.getRequest();
        //设置请求url
        this.url = request.getRequestURI();
        //设置ip
        this.ip = IpUtils.getIp(request);//来自若依框架
        //设置用户信息
        if (GuardianContext.isLogin()) {
            this.user = GuardianContext.getUser();
            this.userType = GuardianContext.getUserType();
        }
    }

    @Override
    public void endLog(Object result) {
        calculateTimeCost();//计算请求耗时
        if(!(result instanceof CommonResult)) {
            throw new RuntimeException(this.signature + "返回类不是CommonResult:" + (result == null ? "null" : result.getClass().getSimpleName()));
        }else {
            recordCommonResult((CommonResult<?>) result);
        }

        HttpServletResponse response = NewxWebUtils.getResponse();
        this.status = response.getStatus();

        //返回code==0时代表成功
        this.success = Objects.equals(this.code, WebPlusManager.webPlusProperties.getResult().getSuccessCode());
    }


    private void recordCommonResult(CommonResult<?> result){
        this.code = result.getCode();
        this.msg = result.getMsg();
        String data = JsonUtils.parse(result.getData());
        if (data.length() > 50000) {
            data = data.substring(0, 50000);
        }
        this.data = data;
    }

    private void calculateTimeCost(){
        long start = this.createTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
        this.timeCost = Math.toIntExact(System.currentTimeMillis() - start);
    }


    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public CommonResultLog setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public Integer getTimeCost() {
        return timeCost;
    }

    public CommonResultLog setTimeCost(Integer timeCost) {
        this.timeCost = timeCost;
        return this;
    }

    public Object getUser() {
        return user;
    }

    public CommonResultLog setUser(Object user) {
        this.user = user;
        return this;
    }

    public String getUserType() {
        return userType;
    }

    public CommonResultLog setUserType(String userType) {
        this.userType = userType;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public CommonResultLog setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getIp() {
        return ip;
    }

    public CommonResultLog setIp(String ip) {
        this.ip = ip;
        return this;
    }

    public String getSignature() {
        return signature;
    }

    public CommonResultLog setSignature(String signature) {
        this.signature = signature;
        return this;
    }

    public String getArgs() {
        return args;
    }

    public CommonResultLog setArgs(String args) {
        this.args = args;
        return this;
    }

    public String getData() {
        return data;
    }

    public CommonResultLog setData(String data) {
        this.data = data;
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public CommonResultLog setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public CommonResultLog setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public CommonResultLog setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public Boolean getSuccess() {
        return success;
    }

    public CommonResultLog setSuccess(Boolean success) {
        this.success = success;
        return this;
    }

}
