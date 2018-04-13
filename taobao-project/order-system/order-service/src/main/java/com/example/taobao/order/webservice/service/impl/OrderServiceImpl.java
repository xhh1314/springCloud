package com.example.taobao.order.webservice.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.example.taobao.order.api.OrderService;

@Service( version = "1.0.0",application = "${dubbo.application.id}", protocol = "${dubbo.protocol.id}", registry = "${dubbo.registry.id}")
public class OrderServiceImpl implements OrderService {
    @Override
    public String getOrderDetailsByUserName(Integer userId) {

        return "成功用户"+userId+"获取订单信息!";
    }
}
