package com.example.myoauth.resources.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping
public class ResourceProtectController {


    @RequestMapping(value = "/index")
    @ResponseBody
    public String index() {
        return "protected resources!";
    }
}
