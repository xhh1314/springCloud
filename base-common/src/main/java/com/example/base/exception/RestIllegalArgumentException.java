package com.example.base.exception;

/**
 * Rest参数异常
 */
public class RestIllegalArgumentException extends IllegalArgumentException {

    public RestIllegalArgumentException(String s) {
        super(s);
    }
}
