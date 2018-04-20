package com.example.taobao.order.common.dao;

import com.example.taobao.order.common.entity.OrderItemDO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemMapper {

    List<OrderItemDO> getOrderItemByOrderId(Integer orderId);
}
