package com.example.taobao.common.exception;

/**
 * 获取jedis cluster 连接异常
 *
 * @author lh
 * @date 2018/4/22
 * @since
 */
public class JedisGetConnectionException extends RuntimeException {
    public JedisGetConnectionException() {
        super();
    }

    public JedisGetConnectionException(String message) {
        super(message);
    }

    public JedisGetConnectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public JedisGetConnectionException(Throwable cause) {
        super(cause);
    }

    protected JedisGetConnectionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
