package com.landao.web.plus.utils;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;


public class JsonUtils{

    public static ObjectMapper objectMapper;

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper){
        JsonUtils.objectMapper=objectMapper;
    }

    public static String parse(Object obj){
        if(obj==null){
            return "null";
        }
        String res=null;
        try {
            res=objectMapper.writeValueAsString(obj);
        }catch (Exception e){
            res=obj.toString();
        }
        return res;
    }

}
