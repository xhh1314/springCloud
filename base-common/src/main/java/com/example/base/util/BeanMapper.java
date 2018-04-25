package com.example.base.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.sun.xml.internal.ws.encoding.soap.SerializationException;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import static com.alibaba.fastjson.parser.Feature.InitStringFieldAsEmpty;
import static com.alibaba.fastjson.serializer.SerializerFeature.WriteNullListAsEmpty;

@Component
public class BeanMapper <T>{

    /**
     * 把bean实例转换成Map<String,String> ,方便redis存储
     *
     * @param obj
     * @return
     */
    public static Map<String, String> mapBeanToStringMap(Object obj) {
       final Map<String, String> map = new HashMap<>(32);
        String strVal = JSON.toJSONString(obj, WriteNullListAsEmpty);
        try {
            strVal = new String(strVal.getBytes(), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Map<String, Object> temp = JSON.parseObject(strVal, Map.class, InitStringFieldAsEmpty);
        if (temp.size() == 0)
            return map;
        temp.forEach((s, o) -> {
            if (o instanceof String) {
                map.put(s, (String) o);
            } else if (o != null) {
                map.put(s, o.toString());
            }
        });
        map.size();
        return map;
    }

    /**
     * 把Map<String,String>转换成Bean
     *
     * @param map
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T mapToBean(Map<String, String> map, Class<T> type) {
        return JSON.parseObject(JSON.toJSONString(map), type);
    }

    private static final FastJsonConfig fastJsonConfig = new FastJsonConfig();
    public  byte[] getBytes(T object){
        if(object==null)
            return new byte[0];
        try {
            return JSON.toJSONBytes(object, fastJsonConfig.getSerializeConfig(), fastJsonConfig.getSerializerFeatures());
        } catch (Exception ex) {
            throw new SerializationException("Could not serialize: " + ex.getMessage(), ex);
        }

    }

    public   T deserialize(byte[] bytes ,Class<T> type) throws SerializationException {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        try {
            return (T) JSON.parseObject(bytes, type, fastJsonConfig.getFeatures());
        } catch (Exception ex) {
            throw new SerializationException("Could not deserialize: " + ex.getMessage(), ex);
        }
    }
}
