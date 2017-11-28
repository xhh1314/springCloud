package com.example.springcloud.rabbitmqtest.test;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author 李浩
 * @date ${DATE}
 */
public class Recv {
    private final static String QUEUE_NAME = "hello1";
    private final static String exchangeName = "logs";

    public static void main(String[] argv)
            throws java.io.IOException,
            java.lang.InterruptedException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        String queueName = channel.queueDeclare().getQueue();
        channel.exchangeDeclare(exchangeName,BuiltinExchangeType.FANOUT);
        channel.queueBind(queueName, exchangeName,"");
       // channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        Consumer consumer=new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
            String message=new String(body,"utf-8");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(message);
            }
        };
       // channel.basicConsume(QUEUE_NAME,consumer);
        channel.basicConsume(queueName,false, consumer);
    }

}