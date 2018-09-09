package com.example.springcloud.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * 供service-b调用
 *
 * @author lh
 * @date 2018/7/10
 * @since
 */
@RequestMapping(value = "/test")
@RestController
public class GetMyNameController {

    @RequestMapping(value = "getMyName")
    public String getMyName(String name) {

        if(1==1) {
            System.out.println("-------------异常----------------");
            //throw new RuntimeException("熔断异常!");
        }
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "my name is:" + name;
    }
}
