package com.example.taobao.order.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.sun.xml.internal.ws.encoding.soap.SerializationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static com.alibaba.fastjson.serializer.SerializerFeature.WriteNullListAsEmpty;

@Component
public class SerializeUtil<T> {
    private static final FastJsonConfig fastJsonConfig = new FastJsonConfig();

    public static final Logger log = LoggerFactory.getLogger(SerializeUtil.class);

    public byte[] toBytes(Object object) {
        if (object == null)
            return new byte[0];
        try {
            return JSON.toJSONBytes(object, fastJsonConfig.getSerializeConfig(), fastJsonConfig.getSerializerFeatures());
        } catch (Exception ex) {
            throw new SerializationException("Could not serialize: " + ex.getMessage(), ex);
        }

    }

    public T deserialize(byte[] bytes, Class<T> type) throws SerializationException {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        try {
            return (T) JSON.parseObject(bytes, type, fastJsonConfig.getFeatures());
        } catch (Exception ex) {
            throw new SerializationException("Could not deserialize: " + ex.getMessage(), ex);
        }
    }

    /**
     * 对象转换成
     * @param object
     * @return
     */
    public Map<byte[], byte[]> toMapBytes(Object object) {
        Map<byte[], byte[]> byteMap = new HashMap<>(32);
        String strVal = JSON.toJSONString(object, WriteNullListAsEmpty);
        try {
            strVal = new String(strVal.getBytes(), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Map<String, Object> temp = JSON.parseObject(strVal, Map.class);
        if (temp.size() == 0)
            return Collections.emptyMap();
        for (Map.Entry<String, Object> entry : temp.entrySet()) {
            if (entry.getValue() == null)
                continue;
            byte[] key = null;
            try {
                key = entry.getKey().getBytes("utf-8");
            } catch (UnsupportedEncodingException e) {
                log.error("不支持的编码格式!{}", e);
            }
            byte[] val = toBytes(entry.getValue());
            byteMap.put(key, val);
        }
        return byteMap;
    }

}
