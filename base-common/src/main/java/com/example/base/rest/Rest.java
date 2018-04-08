package com.example.base.rest;

import com.example.base.exception.RestIllegalArgumentException;

import java.util.HashMap;
import java.util.Map;


/**
 * 远程调用json封装
 *
 * @author lh
 * @date 2018/1/22
 * @since
 */
public class Rest {
    /**
     * 状态码
     */
    private int code;
    /**
     * 是否成功返回所需数据
     */
    private boolean success;
    /**
     * 消息描述
     */
    private String message;
    /**
     * 数据存放体
     */
    private Object data;

    public static Rest success(String message) {
        Rest rest = new Rest();
        rest.message = message;
        rest.code = 200;
        rest.success = true;
        return rest;
    }

    /**
     * 传入数据和消息，
     *
     * @param data
     * @param message
     * @return
     */
    public static Rest success(Object data, String message) {
        Rest rest = new Rest();
        rest.message = message;
        rest.code = 200;
        rest.data = data;
        rest.success = true;
        return rest;
    }

    public static Rest success(Object data, int code) {
        Rest rest = new Rest();
        rest.code = 200;
        rest.data = data;
        rest.code = code;
        rest.success = true;
        return rest;
    }

    public static Rest success(Map<String, Object> data, String message) {
        Rest rest = new Rest();
        rest.message = message;
        rest.code = 200;
        rest.data = data;
        rest.success = true;
        return rest;
    }

    public static Rest success(Object data) {
        Rest rest = new Rest();
        rest.message = "success";
        rest.code = 200;
        rest.data = data;
        rest.success = true;
        return rest;
    }

    /**
     * 直接传一个map作为值,适用于需要传多个值的情况
     *
     * @param data
     * @return
     */
    public static Rest success(Map<String, Object> data) {
        Rest rest = new Rest();
        rest.success = true;
        rest.message = "success";
        rest.code = 200;
        rest.data = data;
        return rest;
    }

    public static Rest failure(String message) {
        Rest rest = new Rest();
        rest.message = message;
        rest.code = 500;
        return rest;
    }

    public static Rest failure(Object data) {
        Rest rest = new Rest();
        rest.message = "failure";
        rest.code = 500;
        rest.data = data;
        return rest;
    }

    public static Rest failure(int code) {
        Rest rest = new Rest();
        rest.message = "failure";
        rest.code = code;
        return rest;
    }

    public static Rest failure(int code, String message) {
        Rest rest = new Rest();
        rest.message = message;
        rest.code = code;
        return rest;
    }

    public static Rest failure(Object data, String message, int code) {
        Rest rest = new Rest();
        rest.message = message;
        rest.code = code;
        rest.data = data;
        return rest;
    }


    /**
     * 往data里put 键值对，当需要传多个值的时候，可以使用该方法，如果只传一个值，请不要使用该方法！ 可以使用静态方法就行
     *
     * @param key
     * @param data
     * @return
     */
    public Rest put(String key, Object data) {
        if (this.data != null && !(this.data instanceof Map))
            throw new RestIllegalArgumentException("不合法的数据类型！不能同时给data赋值map 非map类型 的值!");
        Map<String, Object> map = null;
        if (this.data == null) {
            map = new HashMap<String, Object>(4);
            this.data = map;
        } else {
            map = (Map) this.data;
        }
        map.put(key, data);
        return this;
    }


    public int getCode() {
        return code;
    }

    public Rest setCode(int code) {
        this.code = code;
        return this;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

