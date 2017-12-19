package com.example.springcloud.bankofchina.manage;

public class Restful {
    public static final String OK = "ok";
    public static final String ERROR = "error";
    private final Meta meta;
    private final Object data;

    Restful(Meta meta, Object data) {
        this.meta = meta;
        this.data = data;
    }

    /**
     * 成功，带数据
     * @param data
     * @return
     */
    public static Restful success(Object data) {
        return new Restful(new Meta(true, OK), data);
    }

    /**
     * 成功，带数据和消息
     * @param data
     * @param message
     * @return
     */
    public static Restful success(Object data, String message) {
        return new Restful(new Meta(true, message), data);
    }

    /**
     * 成功，带默认消息ok
     * @return
     */
    public static Restful success() {
        return new Restful(new Meta(true, OK), null);
    }

    public static Restful failure(String message) {

        return new Restful(new Meta(false, message), null);
    }

    public static Restful failure(Object data, String message) {
        return new Restful(new Meta(false, message), data);
    }

    public static Restful failure() {
        return new Restful(new Meta(false, ERROR), null);
    }

    public Meta getMeta() {
        return meta;
    }

    public Object getData() {
        return this.data;
    }
}
