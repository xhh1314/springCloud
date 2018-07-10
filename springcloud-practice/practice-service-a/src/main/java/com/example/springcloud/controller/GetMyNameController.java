package com.example.springcloud.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(value = "getMyName1")
    public String getMyName(String name) {
        return "my name is:" + name;
    }
}
