package com.example.springcloud.controller;

import com.example.springcloud.practice.api.FeignGetMyName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 测试hystrix的timeout 与 ribbon重试
 *
 * 在service-a中写一个类，通过睡眠等方式测试readTimeout 等配置
 * @author lh
 * @date 2018/7/10
 * @since
 */
@Controller
@ResponseBody
public class HystrixTestController {

    @Autowired
    private FeignGetMyName feiginGetMyName;

    @RequestMapping(value = "/test")
    public String test(String name) {
        return feiginGetMyName.getMyName(name);
    }
}
