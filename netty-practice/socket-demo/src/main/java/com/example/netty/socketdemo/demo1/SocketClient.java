package com.example.netty.socketdemo.demo1;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class SocketClient {
    public static void main(String[] args) throws IOException {
        // 要连接的服务端IP地址和端口
        String host = "127.0.0.1";
        int port = 55533;
        // 与服务端建立连接
        Socket socket = new Socket(host, port);
        OutputStream outputStream=socket.getOutputStream();
        String message="hello Mr li ";
        outputStream.write(message.getBytes("utf-8"));
        socket.close();
    }
}
