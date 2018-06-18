package com.example.test.aop.springaoptest;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.stereotype.Component;


import java.lang.reflect.Method;

@Component(value = "myBeforeAdvice")
public class MyBeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("----------执行前置方法!----------");
    }
}
