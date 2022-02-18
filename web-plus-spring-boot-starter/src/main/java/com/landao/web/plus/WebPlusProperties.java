package com.landao.web.plus;


import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties("spring.web.plus")
public class WebPlusProperties {

    private DateFormat dateFormat=new DateFormat();

    private Result result=new Result();

    private MybatisPlus mybatisPlus=new MybatisPlus();

    public WebPlusProperties() {

    }

    public DateFormat getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public MybatisPlus getMybatisPlus() {
        return mybatisPlus;
    }

    public void setMybatisPlus(MybatisPlus mybatisPlus) {
        this.mybatisPlus = mybatisPlus;
    }

    /**
     * 返回结果
     */
    public static class Result{

        private Integer successCode=0;

        private Integer errorCode=-1;

        public Result() {
        }

        public Integer getSuccessCode() {
            return successCode;
        }

        public void setSuccessCode(Integer successCode) {
            this.successCode = successCode;
        }

        public Integer getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(Integer errorCode) {
            this.errorCode = errorCode;
        }
    }

    public static class DateFormat{
        private String dateTime = "yyyy-MM-dd HH:mm";
        private String date = "yyyy-MM-dd";
        private String time = "HH:mm";

        public DateFormat() {
        }

        public String getDateTime() {
            return dateTime;
        }

        public void setDateTime(String dateTime) {
            this.dateTime = dateTime;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }

    public static class MybatisPlus{

        private String createTime="createTime";

        private String updateTime="updateTime";

        public MybatisPlus() {
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }
    }


}
