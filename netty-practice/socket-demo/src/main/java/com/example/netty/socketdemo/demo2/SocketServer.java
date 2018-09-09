package com.example.netty.socketdemo.demo2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
    public static void main(String[] args) throws IOException {
        int port = 55533;
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("wait for connect !");
        InputStream inputStream = null;
        OutputStream outputStream=null;
        Socket socket = null;
        boolean flag=true;
        int count=0;
        while (flag) {
            socket = serverSocket.accept();
            inputStream = socket.getInputStream();
            byte[] bytes = new byte[1024];
            StringBuilder message = new StringBuilder(2048);
            int len = 0;
            while ((len = inputStream.read(bytes)) > 0) {
                message.append(new String(bytes, 0, len, "utf-8"));
            }
            System.out.println("receive message: " + message);
            outputStream=socket.getOutputStream();
            outputStream.write((count+++"receive message ").getBytes("utf-8"));
            //需要把inputStream关闭才能把消息发出去
            inputStream.close();
        }
        socket.close();
        outputStream.close();
        serverSocket.close();
    }
}
