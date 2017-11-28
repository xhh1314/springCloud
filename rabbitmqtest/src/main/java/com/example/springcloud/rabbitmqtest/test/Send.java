package com.example.springcloud.rabbitmqtest.test;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author 李浩
 * @date ${date}
 */
public class Send {


    public static void main(String[] argv) throws IOException, TimeoutException {

        Thread thread1= new Thread(new MessageTest());
        Thread thread2= new Thread(new MessageTest());
        Thread thread3= new Thread(new MessageTest());
        Thread thread4= new Thread(new MessageTest());

        thread1.start();
       // thread2.start();
       // thread3.start();
       // thread4.start();

    }
}

class MessageTest implements Runnable {
    private final static String QUEUE_NAME = "logs";
    private final static String exchangeName = "logs";
    static ConnectionFactory factory = new ConnectionFactory();
    static Connection connection;
    static Channel channel;

    static {
        factory.setHost("localhost");
        try {
            connection = factory.newConnection();
            channel = connection.createChannel();
            channel.exchangeDeclare(exchangeName, BuiltinExchangeType.FANOUT);

           // channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }


    }
    String message = "Hello World!";

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                channel.basicPublish(exchangeName, "", null, (Thread.currentThread().getName()+":"+message + i).getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
