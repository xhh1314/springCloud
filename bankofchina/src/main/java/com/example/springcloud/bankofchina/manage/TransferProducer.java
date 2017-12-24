package com.example.springcloud.bankofchina.manage;

import com.alibaba.fastjson.JSON;
import com.example.springcloud.bankofchina.entity.BalanceDO;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * rocketmq发送转账消息的生产端
 */
@Component
public class TransferProducer implements ApplicationRunner {
    private static final Logger log = LoggerFactory.getLogger("TransferProducer");

    private final DefaultMQProducer producer = new DefaultMQProducer("transferMoney");
    private String topic = "transfer";
    private String namesrvAddr = "127.0.0.1:9876";
    /**
     * 记录发送消息次数
     */
    private AtomicInteger count=new AtomicInteger(0);

    @PostConstruct
    public void init() {
        producer.setNamesrvAddr(namesrvAddr);
    }

    /**
     * 发送转账信息
     *
     * @param tag    转账银行
     * @return
     */
    public boolean sendTransferMessage(String tag, BalanceDO balanceDO) throws MQClientException, UnsupportedEncodingException, RemotingException, InterruptedException, MQBrokerException {
        String json= JSON.toJSONString(balanceDO);
        Message msg = new Message(topic, tag, balanceDO.getBalanceId().toString(),json.getBytes("utf-8"));
        SendResult result = producer.send(msg);
        log.info("计数器:{},发送消息成功:{}",count.addAndGet(1),result);
        return result.getSendStatus() != SendStatus.SLAVE_NOT_AVAILABLE ? true : false;
    }

    /**
     * 注册个hook，当springboot启动成功后，启动producer实例
     *
     * @param applicationArguments
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        producer.start();
    }

    @PreDestroy
    public void destory() {
        producer.shutdown();
    }
}
