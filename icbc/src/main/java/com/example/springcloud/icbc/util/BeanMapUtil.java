package com.example.springcloud.icbc.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.Map;

public abstract class BeanMapUtil {

    private static final Logger log = LoggerFactory.getLogger(BeanMapUtil.class);

    public static <T> T mapToBean(Map<String, String> map, Class<T> target) {
        T object = null;
        try {
            object = target.newInstance();
            Field[] fields = target.getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                Object value = map.get(fields[i].getName());
                if (value != null) {
                    fields[i].setAccessible(true);
                    fields[i].set(object, value);
                }
            }
        } catch (InstantiationException e) {
            log.error("map转换成bean出现异常!{}", e);
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            log.error("map转换成bean出现异常!{}", e);
            throw new RuntimeException(e);
        }
        return object;
    }
}
