package com.example.netty.socketdemo.demo2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SocketClient {
    public static void main(String[] args) throws IOException {
        String host = "127.0.0.1";
        String hostRemote = "10.1.11.28";
        int port = 55533;
        Socket socket = new Socket(hostRemote, port);
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
