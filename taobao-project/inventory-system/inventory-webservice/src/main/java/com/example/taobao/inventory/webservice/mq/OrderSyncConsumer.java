package com.example.taobao.inventory.webservice.mq;

import com.alibaba.fastjson.JSON;
import com.example.taobao.inventory.webservice.service.LogisticsService;
import com.example.taobao.order.dto.OrderDTO;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus.CONSUME_SUCCESS;

/**
 * 库存系统监听订单下达指令
 *
 * @author lh
 * @date 2018/4/26
 * @since
 */
@Service
public class OrderSyncConsumer implements ApplicationRunner {

    public static final Logger log = LoggerFactory.getLogger(OrderSyncConsumer.class);

    private String group = "taobao";

    private String nameAddress="10.1.11.28:9876";

    @Autowired
    private LogisticsService logisticsService;


    private void startOrderConsumer() throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(group);
        consumer.setNamesrvAddr(nameAddress);
        consumer.subscribe("order", "*");
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.setConsumeTimestamp(new Date().getTime() + "");
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {

                try {
                    for (MessageExt messageExt : msgs) {
                        byte[] bytes = messageExt.getBody();
                        OrderDTO orderDTO = JSON.parseObject(bytes, OrderDTO.class);
                        logisticsService.createLogisticsByOrder(orderDTO);
                        log.info("收到订单消息,创建物流信息成功!,订单号:{}",orderDTO.getOrderId());
                    }
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                } catch (Exception e) {
                    log.error("创建物流订单发生异常!{}", e);
                    return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                }


            }
        });


    }

    /**
     * 注册的hook,当服务启动后调用该方法
     *
     * @param args
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        startOrderConsumer();
    }
}
