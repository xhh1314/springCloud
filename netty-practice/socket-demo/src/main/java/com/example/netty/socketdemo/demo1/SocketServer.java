package com.example.netty.socketdemo.demo1;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
    public static void main(String[] args) throws IOException {
        int port = 55533;
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("wait for connect !");
        InputStream inputStream = null;
        Socket socket = null;
        boolean flag=true;
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
        }
        inputStream.close();
        socket.close();
        serverSocket.close();
    }
}
