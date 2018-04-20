package com.example.taobao.order.webservice.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.example.taobao.order.api.OrderService;
import com.example.taobao.order.common.manage.OrderManage;
import com.example.taobao.order.dto.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

@Service(version = "1.0.0", application = "${dubbo.application.id}", protocol = "${dubbo.protocol.id}", registry = "${dubbo.registry.id}")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderManage orderManage;

    @Override
    public String getOrderDetailsByUserName(Integer userId) {

        return "成功用户" + userId + "获取订单信息!";
    }

    @Override
    public void updateOrderSuccessByOrderId(Integer orderId) {
        OrderDTO orderDTO = orderManage.getOrderById(orderId);
        orderDTO.setPayTime(new Date());
        orderDTO.setStatus((short) 1);
        orderManage.updateOrder(orderDTO);
    }
}
