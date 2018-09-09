package com.example.netty.socketdemo.demo3;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketClient {
    public static void main(String[] args) throws IOException {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 100; i++) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        sendMessage();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
            executorService.submit(runnable);
        }

    }

    private static void sendMessage() throws IOException {
        String host = "127.0.0.1";
        String hostRemote = "10.1.11.28";
        int port = 55533;
        Socket socket = new Socket(host, port);
        OutputStream outputStream = socket.getOutputStream();
        String message = "hello Mr li ";
        outputStream.write(message.getBytes("utf-8"));
        socket.shutdownOutput();
        InputStream inputStream = socket.getInputStream();
        byte[] bytes = new byte[1024];
        int length = 0;
        StringBuilder builder = new StringBuilder(1024);
        while ((length = inputStream.read(bytes)) > 0) {
            builder.append(new String(bytes, 0, length, "utf-8"));
        }
        System.out.println(builder);
        outputStream.close();
        inputStream.close();
        socket.close();
    }
}
