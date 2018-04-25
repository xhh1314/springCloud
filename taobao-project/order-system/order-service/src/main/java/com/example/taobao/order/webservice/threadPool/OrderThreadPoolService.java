package com.example.taobao.order.webservice.threadPool;

import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Service
public class OrderThreadPoolService {

    private final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 10, 10, TimeUnit.SECONDS, new ArrayBlockingQueue(10000));


    public void executeTask(Runnable runnable) {
        threadPoolExecutor.submit(runnable);
    }

    @PreDestroy
    public void destory(){
        threadPoolExecutor.shutdown();

    }
}
