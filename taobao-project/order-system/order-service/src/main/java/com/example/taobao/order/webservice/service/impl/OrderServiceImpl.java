package com.example.taobao.order.webservice.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.example.taobao.order.api.OrderService;
import com.example.taobao.order.common.manage.OrderManage;
import com.example.taobao.order.dto.OrderDTO;
import com.example.taobao.order.webservice.mq.OrderSyncProducer;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service(version = "1.0.0", application = "${dubbo.application.id}", protocol = "${dubbo.protocol.id}", registry = "${dubbo.registry.id}")
public class OrderServiceImpl implements OrderService {

    public static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderManage orderManage;

    @Autowired
    private OrderSyncProducer orderSyncProducer;

    @Override
    public String getOrderDetailsByUserName(Integer userId) {

        return "成功用户" + userId + "获取订单信息!";
    }

    @Override
    public void updateOrderSuccessByOrderId(Integer orderId) {
        OrderDTO orderDTO = orderManage.getOrderById(orderId);
        orderDTO.setPayTime(new Date());
        orderDTO.setStatus((short) 1);
        try {
            updateOrder(orderDTO);
        } catch (InterruptedException e) {
            log.error("提交订单发生异常!{}", e);
        } catch (RemotingException e) {
            log.error("提交订单发生异常!{}", e);
        } catch (MQClientException e) {
            log.error("提交订单发生异常!{}", e);
        } catch (MQBrokerException e) {
            log.error("提交订单发生异常!{}", e);
        }
    }

    @Transactional(rollbackFor = {RuntimeException.class, InterruptedException.class, RemotingException.class, MQBrokerException.class})
    public void updateOrder(OrderDTO orderDTO) throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        orderManage.updateOrder(orderDTO);
        orderSyncProducer.orderSubmit(orderDTO);
    }
}
