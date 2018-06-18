package com.example.springcloud.servicefeign.controller;

import com.example.springcloud.servicefeign.service.SchedualServiceHiFeign;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/hystrix")
public class HystrixTestController {


    @RequestMapping(value = "/test1")
    @HystrixCommand(fallbackMethod = "test1FallBack" )
    public String test1(){
        if(1==1)
            throw new RuntimeException("3");

        return "I am test1";
    }

    public String test1FallBack(){
        return "I am test1 fall back";
        //throw new RuntimeException("3");
    }


}
