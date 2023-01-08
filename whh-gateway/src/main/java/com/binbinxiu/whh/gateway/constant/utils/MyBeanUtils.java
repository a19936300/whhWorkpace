package com.binbinxiu.whh.gateway.constant.utils;

import cn.hutool.core.util.ArrayUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class MyBeanUtils extends BeanUtils {

    public static Map<String, Object> objectToMap(Object obj) throws IllegalAccessException {
        Map<String, Object> map = new HashMap<String, Object>();
        Class<?> clazz = obj.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            String fieldName = field.getName();
            map.put(fieldName, field.get(obj));
        }
        return map;
    }

    /**
     * map转对象
     *
     * @param map
     * @param clazz
     * @param <T>
     * @return
     * @throws IllegalAccessException
     */
    public static <T> T mapToObject(Claims map, Class<T> clazz) {
        try {
            int i = 0;
            T obj = clazz.getDeclaredConstructor().newInstance();
            Field[] superFields = clazz.getSuperclass().getDeclaredFields();
            Field[] sonFields = clazz.getDeclaredFields();
            Field[] fields = ArrayUtil.addAll(superFields, sonFields);
            for (Field field : fields) {
                field.setAccessible(true);
                String fieldName = field.getName();
                if (fieldName.equals("time")) {
                    field.set(obj, map.getExpiration().getTime());
                    i++;
                } else {
                    Object value = map.get(fieldName);
                    if (value != null) {
                        field.set(obj, value);
                        i++;
                    }
                }
            }

            if (i == fields.length) {
                return obj;
            } else {
                return null;
            }
        } catch (IllegalAccessException | InstantiationException e) {
            return null;
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

}
