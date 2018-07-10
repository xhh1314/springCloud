package com.example.taobao.inventory.webservice.mq;

import com.alibaba.fastjson.JSON;
import com.example.taobao.inventory.webservice.service.LogisticsService;
import com.example.taobao.order.dto.OrderDTO;
import org.apache.rocketmq.client.consumer.DefaultMQPullConsumer;
import org.apache.rocketmq.client.consumer.PullResult;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;


import javax.annotation.PreDestroy;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class OrderPullConsumer implements ApplicationRunner {
    private static final Logger log = LoggerFactory.getLogger(OrderPushConsumer.class);
    private final Map<MessageQueue, Long> OFFSE_TABLE = new ConcurrentHashMap<>(128);
    private String group = "taobao";
    private String nameAddress = "10.1.11.28:9876";
    private final DefaultMQPullConsumer consumer = new DefaultMQPullConsumer(group);

    @Autowired
    private LogisticsService logisticsService;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        consumer.setNamesrvAddr(nameAddress);
        //consumer.start();
    }

    public void consume() throws MQClientException {
        Set<MessageQueue> mqs = consumer.fetchSubscribeMessageQueues("order");
        for (MessageQueue mq : mqs) {
            try {
                PullResult pullResult = consumer.pullBlockIfNotFound(mq, null, getMessageQueueOffset(mq), 32);
                System.out.printf("%s%n", pullResult);
                List<MessageExt> messageExtList = pullResult.getMsgFoundList();
                if (messageExtList != null && messageExtList.size() > 0) {
                    for (MessageExt messageExt : messageExtList) {
                        byte[] bytes = messageExt.getBody();
                        OrderDTO orderDTO = JSON.parseObject(bytes, OrderDTO.class);
                        log.info("收到订单消息:{}", orderDTO);
                    }
                }
                putMessageQueueOffset(mq, pullResult.getNextBeginOffset());

            } catch (Exception e) {
                e.printStackTrace();
            }

        }


    }


    @PreDestroy
    public void destroy() {
        consumer.shutdown();
    }

    private long getMessageQueueOffset(MessageQueue mq) {
        Long offset = OFFSE_TABLE.get(mq);
        if (offset != null)
            return offset;

        return 0;
    }

    private void putMessageQueueOffset(MessageQueue mq, long offset) {
        OFFSE_TABLE.put(mq, offset);
    }
}
