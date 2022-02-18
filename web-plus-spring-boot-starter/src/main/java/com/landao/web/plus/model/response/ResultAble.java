package com.landao.web.plus.model.response;

public interface ResultAble<T> {

    Integer getCode();

    String getMsg();

    T getData();

}
