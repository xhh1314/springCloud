package com.example.taobao.inventory.webservice.service;

import com.example.taobao.inventory.common.dao.LogisticsMapper;
import com.example.taobao.inventory.common.entity.LogisticsDO;
import com.example.taobao.order.dto.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class LogisticsService {

    @Autowired
    private LogisticsMapper logisticsMapper;

    @Transactional()
    public void createLogisticsByOrder(OrderDTO orderDTO) {
        LogisticsDO logisticsDO = new LogisticsDO();
        logisticsDO.setOrderId(orderDTO.getOrderId());
        logisticsDO.setCreateTime(new Date());
        logisticsDO.setAcceptingOrderTime(new Date());
        logisticsDO.setDeliveryAddress(orderDTO.getDeliveryAddressId());
        logisticsMapper.saveLogistics(logisticsDO);
    }


}
