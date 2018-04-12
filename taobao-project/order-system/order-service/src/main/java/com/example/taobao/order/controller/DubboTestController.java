package com.example.taobao.order.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.example.taobao.rpc.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping
public class DubboTestController {


    @Reference
    private UserService userService;

    @RequestMapping(value = "/index")
    @ResponseBody
    public String index() {

        return userService.getUserByName("lihao");

    }
}
