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

    /**
     * 根据订单信息创建物流信息
     *
     * @param orderDTO
     */
    @Transactional()
    public void createLogisticsByOrder(OrderDTO orderDTO) {
        LogisticsDO logisticsDO = new LogisticsDO();
        logisticsDO.setOrderId(orderDTO.getOrderId());
        logisticsDO.setCreateTime(new Date());
        logisticsDO.setAcceptingOrderTime(new Date());
        logisticsDO.setDeliveryAddress(orderDTO.getDeliveryAddressId());
        logisticsMapper.saveLogistics(logisticsDO);
    }


    public void saveLogistics(LogisticsDO logisticsDO) {
        if (logisticsDO == null)
            return;
        logisticsMapper.saveLogistics(logisticsDO);

    }

    public void updateLogistics(LogisticsDO logisticsDO) {
        LogisticsDO old = logisticsMapper.getLogisticsById(logisticsDO.getLogisticsId());
        if (old == null)
            return;
        copyProperties(logisticsDO, old);
        logisticsMapper.updateLogistics(old);
    }

    private LogisticsDO copyProperties(LogisticsDO origin, LogisticsDO target) {
        if (origin.getAcceptingOrderTime() != null)
            target.setAcceptingOrderTime(origin.getAcceptingOrderTime());
        if (origin.getCreateTime() != null)
            target.setCreateTime(origin.getCreateTime());
        if (origin.getDeliveryAddress() != null)
            target.setDeliveryAddress(origin.getDeliveryAddress());
        if (origin.getDeliveryTime() != null)
            target.setDeliveryTime(origin.getDeliveryTime());
        if (origin.getOrderId() != null)
            target.setOrderId(origin.getOrderId());
        if (origin.getProductId() != null)
            target.setProductId(origin.getProductId());
        if (origin.getReceivingTime() != null)
            target.setReceivingTime(origin.getReceivingTime());
        if (origin.getTransitParticular() != null)
            target.setTransitParticular(origin.getTransitParticular());

        return target;
    }


}
