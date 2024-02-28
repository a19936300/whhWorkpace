package com.binbinxiu.whh.jiudao.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonUtils {
    static ObjectMapper objectMapper = new ObjectMapper();


    /**
     * 将对象转换成字符串
     * @param obj
     * @param <T>
     * @return
     */
    public static  <T> String obj2String(T obj){
        if (obj == null) {
            return null;
        }
        String s = null;
        try {
            s = obj instanceof String ? (String) obj : objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return s;
    }


    /**
     * 字符串转对象
     * @param str
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T string2Obj(String str, Class<T> clazz) {
        if (str == null || str.length() == 0 || clazz == null) {
            return null;
        }
        T t = null;
        try {
            t = clazz.equals(String.class) ? (T) str : objectMapper.readValue(str, clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return t;
    }


}
