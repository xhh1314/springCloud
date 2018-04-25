package com.example.taobao.order.common.manage;

import com.example.base.util.BeanMapper;
import com.example.taobao.order.common.dao.OrderMapper;
import com.example.taobao.order.common.entity.OrderDO;
import com.example.taobao.order.dto.OrderDTO;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCluster;

import java.util.Map;
import java.util.Set;

@Service
public class OrderManage {

    @Autowired
    private JedisCluster jedisCluster;

    @Autowired
    private OrderMapper orderMapper;

    private String orderKey = "order:";

    public Integer saveOrder(OrderDTO orderDTO) {
        OrderDO orderDO = new OrderDO();
        orderDO.transfertoDO(orderDTO);
        return orderMapper.saveOrder(orderDO);

    }

    public void updateOrder(OrderDTO orderDTO) {
        OrderDO orderDO = new OrderDO();
        orderDO.transfertoDO(orderDTO);
        orderMapper.updateOrder(orderDO);
        Map<String, String> stringStringMap = BeanMapper.mapBeanToStringMap(orderDTO);
        jedisCluster.hmset(orderKey + orderDTO.getOrderId(), stringStringMap);

    }

    public OrderDTO getOrderById(Integer orderId) {
        if (orderId == null)
            return null;
        String key = orderKey + orderId;
        OrderDTO orderDTO = null;
        Map<String, String> resultFromCache = jedisCluster.hgetAll(key);
        //缓存中没有取到值
        if (resultFromCache == null || resultFromCache.size()==0) {
            synchronized (key) {
                if (resultFromCache == null || resultFromCache.size()==0) {
                    OrderDO orderDO = orderMapper.getOrderById(orderId);
                    if (orderDO != null) {
                        orderDTO = orderDO.transfertoDTO();
                        resultFromCache = BeanMapper.mapBeanToStringMap(orderDTO);
                        jedisCluster.hmset(key,resultFromCache);
                        return orderDTO;
                    }
                }
            }
        }
        //缓存中取到值
        orderDTO = BeanMapper.mapToBean(resultFromCache, OrderDTO.class);

        return orderDTO;
    }
}
