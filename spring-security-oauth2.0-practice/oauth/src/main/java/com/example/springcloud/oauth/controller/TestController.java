package com.example.springcloud.oauth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping(value = "/test")
@Controller
public class TestController {


    @RequestMapping(value = "/getValue")
    @ResponseBody
    public String test(){

        return "test ok!";
    }
}
