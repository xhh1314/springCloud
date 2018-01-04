package com.example.springcloud.icbc.service;

import com.alibaba.fastjson.JSON;
import com.example.springcloud.icbc.entity.BalanceDO;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


import javax.annotation.PreDestroy;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * rocketmq转账消费端
 */
@Component
public class TransferConsumer implements ApplicationRunner {
    @Autowired
    private BalanceService balanceService;
    private String groupName = "transferMoney";
    private String topic = "transfer";
    private String namesrvAddr = "10.1.11.28:9876";
    private final DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(groupName);
    private static Logger log = LoggerFactory.getLogger("transferConsumer service");
    /**
     * 记录消费次数
     */
    private AtomicInteger count=new AtomicInteger(0);

    public void tranferMessageConsumer() {
        consumer.setMessageModel(MessageModel.BROADCASTING);
        consumer.setNamesrvAddr(namesrvAddr);
        try {
            consumer.subscribe(topic, "icbc");
            consumer.registerMessageListener(new MessageListenerConcurrently() {
                @Override
                public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                    boolean flag=true;
                    for (MessageExt message : msgs) {
                        BalanceDO balanceDO = JSON.parseObject(message.getBody(), BalanceDO.class);
                        //如果有一笔转账不成功那么,把flag设置为false
                        if(!incrementAmount(balanceDO.getBalanceId(), balanceDO.getAmount())){
                            flag=false;
                        }
                        log.info("计数器:{},消费消息:{}", count.addAndGet(1),message);
                    }
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
            });
            consumer.start();
            log.info("转账服务成功启动！");
        } catch (MQClientException e) {
            log.error("转账消费服务发生异常{}", e);
        }


    }
    private boolean incrementAmount(Integer id,Double amount){
        return balanceService.increaseAmount(id,amount).getMeta().isSuccess();
    }


    /**
     * 注册一个hook，当启动springboot之后，启动这个类
     *
     * @param applicationArguments
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        tranferMessageConsumer();
    }

    @PreDestroy
    public void destory() {
        consumer.shutdown();
    }
}
