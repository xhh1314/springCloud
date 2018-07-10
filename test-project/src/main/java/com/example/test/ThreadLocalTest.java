package com.example.test;

import java.util.concurrent.TimeUnit;

public class ThreadLocalTest {


    private static class MyThreadLocal extends ThreadLocal<Integer> {

        @Override
        protected Integer initialValue() {
            return super.initialValue();
        }
    }

    public static void main(String[] args) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (ThreadLocalTest.class) {
                    try {
                        TimeUnit.SECONDS.sleep(20);
                        int i = 0;
                        while (true) {
                            i++;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        });
        thread.setName("testThread");
        thread.start();
        int i = 0, sum = 0;
        synchronized (ThreadLocalTest.class) {
            while (true) {
                sum += i++;
            }
        }
    }
}
