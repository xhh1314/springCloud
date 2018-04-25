package com.example.taobao.order.webservice.mq;

import com.example.taobao.order.common.util.SerializeUtil;
import com.example.taobao.order.dto.OrderDTO;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
public class OrderSyncProducer {


    @Autowired
    private SerializeUtil serializeUtil;

    private String group="order";

    private  final DefaultMQProducer producer=new DefaultMQProducer(group);

    @PostConstruct
    public void initial() throws MQClientException {
        producer.start();
    }

    /**
     * 当用户支付成功之后，把订单信息传入消息队列
     */
    public void orderSubmit(OrderDTO orderDTO) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        Message message=new Message("order","",orderDTO.getOrderId().toString(), serializeUtil.toBytes(orderDTO));
        SendResult sendResult=producer.send(message);
        System.out.println(sendResult);

    }

    @PreDestroy
    public void destroy(){
        producer.shutdown();
    }
}
