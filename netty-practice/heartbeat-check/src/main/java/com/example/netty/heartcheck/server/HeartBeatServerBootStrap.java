package com.example.netty.heartcheck.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

import java.net.InetSocketAddress;

public class HeartBeatServerBootStrap {

	private int port;

	public HeartBeatServerBootStrap(int port) {
		this.port = port;
	}

	private void start() throws InterruptedException {
        EventLoopGroup boss=new NioEventLoopGroup();
        EventLoopGroup worker=new NioEventLoopGroup();
        ServerBootstrap bootstrap=new ServerBootstrap();
        try {
            bootstrap.group(boss,worker)
                    .localAddress(new InetSocketAddress(port))
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY,true)
                    .childOption(ChannelOption.SO_KEEPALIVE,true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline p=ch.pipeline();
                            p.addLast(new ObjectEncoder());
                            p.addLast(new ObjectDecoder(ClassResolvers.cacheDisabled(null)));
                            p.addLast(new HeartbeatCheckServerChannelHandler());
                        }
                    });
            ChannelFuture future=bootstrap.bind().sync();
            if(future.isSuccess()){
                System.out.println("服务成功启动！");
            }
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally{
            worker.shutdownGracefully().sync();
            boss.shutdownGracefully().sync();
        }

    }

    public static void main(String[] args) {
        HeartBeatServerBootStrap bootStrap=new HeartBeatServerBootStrap(8080);
        try {
            bootStrap.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }




}
