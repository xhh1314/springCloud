package com.example.springcloud.eurekaclient;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

public class ProducerTest {

    private static final Logger log = LoggerFactory.getLogger("TransferProducer");

    public static void main(String[] args) throws InterruptedException, RemotingException, MQClientException, MQBrokerException, UnsupportedEncodingException {
        DefaultMQProducer producer = new DefaultMQProducer("transferMoney");
        String topic = "transfer1";
        String namesrvAddr = "127.0.0.1:9876";
        producer.setNamesrvAddr(namesrvAddr);
        producer.start();
        Message msg = new Message(topic, "icbc", "1", "amount:2d".getBytes("utf-8"));
        SendResult result = producer.send(msg);
        log.info("发送消息成功:{}", result);
        producer.shutdown();
    }

}
