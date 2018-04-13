package com.example.taobao.order.webservice.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.example.taobao.user.api.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping
public class DubboTestController {


    @Reference(version = "1.0.0",application = "${dubbo.application.id}",url = "dubbo://127.0.0.1:20880")
    private UserService userService;

    @RequestMapping(value = "/index")
    @ResponseBody
    public String index(String name) {

        return userService.getUserByName(name);

    }
}
