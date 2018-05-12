package com.example.netty.file.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;

import java.net.InetSocketAddress;

public class HttpFileServer {

	private int port;

	public HttpFileServer(int port) {
		this.port = port;
	}

	private void start() {
		EventLoopGroup group = new NioEventLoopGroup();

		ServerBootstrap bootstrap = new ServerBootstrap();
		try {
			bootstrap.group(group).localAddress(new InetSocketAddress(port)).channel(NioServerSocketChannel.class)
					.childHandler(new ChannelInitializer<SocketChannel>() {
						@Override
						protected void initChannel(SocketChannel ch) throws Exception {
							ch.pipeline().addLast("http-decoder", new HttpRequestDecoder());
							ch.pipeline().addLast("http-aggregator", new HttpObjectAggregator(65536));
							ch.pipeline().addLast("http-encoder", new HttpResponseEncoder());
							ch.pipeline().addLast("http-chunked", new ChunkedWriteHandler());
							ch.pipeline().addLast("httpFileServerHandler", new HttpFileServerHandler());
						}
					});
			ChannelFuture future = bootstrap.bind().sync();
			System.out.println("http file server start!");
			future.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			group.shutdownGracefully();
		}

	}

	public static void main(String[] args) {
		HttpFileServer fileServer = new HttpFileServer(8081);
		fileServer.start();
	}

}
