package com.example.taobao.inventory.webservice.controller;

import com.example.base.rest.Rest;
import com.example.taobao.inventory.webservice.mq.OrderPullConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/logistics")
public class LogisticsController {
    @Autowired
    private OrderPullConsumer orderPullConsumer;

    @RequestMapping(value = "/getOrderInfo")
    @ResponseBody
    public Rest getOrderInfo() {
        try {
            orderPullConsumer.consume();
        } catch (MQClientException e) {
            e.printStackTrace();
            return Rest.failure(e);
        }
        return Rest.success();
    }
}
