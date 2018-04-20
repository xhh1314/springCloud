package com.example.taobao.order.api;

public interface OrderService {

    String getOrderDetailsByUserName(Integer userId);

    void updateOrderSuccessByOrderId(Integer orderId);
}
