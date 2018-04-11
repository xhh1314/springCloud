package com.oauth.client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/oauth")
public class IndexController {


    @RequestMapping(value = "/getOk")
    @ResponseBody
    public String getOk() {
        return "data:被保护资源";
    }
}
