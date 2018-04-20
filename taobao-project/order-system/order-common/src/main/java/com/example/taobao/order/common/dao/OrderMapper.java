package com.example.taobao.order.common.dao;

import com.example.taobao.order.common.entity.OrderDO;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMapper {

    Integer saveOrder(OrderDO orderDO);

    void updateOrder(OrderDO orderDO);

    OrderDO getOrderById(Integer orderId);
}
