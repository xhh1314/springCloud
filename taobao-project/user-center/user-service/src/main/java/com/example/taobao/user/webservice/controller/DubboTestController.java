package com.example.taobao.user.webservice.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.example.taobao.order.api.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping
public class DubboTestController {

    //必须要version参数，不然会报错很奇怪
    @Reference(version = "1.0.0", application = "${dubbo.application.id}", url = "dubbo://127.0.0.1:20881")
    private OrderService orderService;

    @RequestMapping(value = "/index")
    @ResponseBody
    public String index(Integer userId) {
        String a="v";
        return orderService.getOrderDetailsByUserName(userId);
    }
}
