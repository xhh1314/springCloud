package com.example.netty.socketdemo.demo3;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class SocketServer {
    public static void main(String[] args) throws IOException {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        int port = 55533;
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("wait for connect !");
        boolean flag = true;
        while (flag) {
            Socket socket = serverSocket.accept();
            Runnable runable = new Runnable() {
                @Override
                public void run() {
                    try {
                        int count = 0;
                        InputStream inputStream = socket.getInputStream();
                        byte[] bytes = new byte[1024];
                        StringBuilder message = new StringBuilder(2048);
                        int len = 0;
                        while ((len = inputStream.read(bytes)) > 0) {
                            message.append(new String(bytes, 0, len, "utf-8"));
                        }
                        System.out.println(Thread.currentThread().getName()+":receive message: " + message);

                        OutputStream outputStream = socket.getOutputStream();
                        outputStream.write((count++ + "receive message ").getBytes("utf-8"));
                        inputStream.close();
                        //需要把inputStream关闭才能把消息发出去
                        outputStream.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            };
            executorService.submit(runable);

        }
        serverSocket.close();
    }
}
