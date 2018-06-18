package com.example.test.aop.springaoptest;

import org.springframework.stereotype.Service;


public class PersonImpl implements Person {
    @Override
    public void say() {
        System.out.println("---------执行say方法----------");
        next();
    }


    public void next(){
        System.out.println("--------执行next方法-------------");
    }
}
