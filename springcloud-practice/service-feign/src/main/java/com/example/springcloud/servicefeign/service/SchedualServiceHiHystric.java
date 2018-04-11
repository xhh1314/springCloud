package com.example.springcloud.servicefeign.service;

import org.springframework.stereotype.Component;

@Component
public class SchedualServiceHiHystric implements SchedualServiceHi  {
    @Override
    public String sayHiFromClientOne(String name) {
        return "sorror"+name+"is down";
    }
}
