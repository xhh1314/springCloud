package com.example.taobao.order.common.manage;

import com.example.taobao.order.common.dao.OrderMapper;
import com.example.taobao.order.common.entity.OrderDO;
import com.example.taobao.order.dto.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderManage {

    @Autowired
    private OrderMapper orderMapper;

    public Integer saveOrder(OrderDTO orderDTO) {
        OrderDO orderDO = new OrderDO();
        orderDO.transfertoDO(orderDTO);
        return orderMapper.saveOrder(orderDO);

    }

    public void updateOrder(OrderDTO orderDTO) {
        OrderDO orderDO = new OrderDO();
        orderDO.transfertoDO(orderDTO);
        orderMapper.updateOrder(orderDO);

    }

    public OrderDTO getOrderById(Integer orderId) {
        OrderDO orderDO = orderMapper.getOrderById(orderId);
        if (orderDO == null)
            return null;
        return orderDO.transfertoDTO();
    }
}
