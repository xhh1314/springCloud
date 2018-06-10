package com.example.netty.heartcheck.client;

import com.example.netty.heartcheck.constant.ClientIdentity;
import com.example.netty.heartcheck.message.AKSMessage;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.timeout.IdleStateHandler;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

public class HeartbeatClientBootStrap {

	private static String address;
	private static int port;

	private volatile static SocketChannel socketChannel = null;

	private static volatile boolean isPrepared = false;

	private static long delay = 1;
	private volatile static long reconnectCount = 0;

	private static final Bootstrap bootstrap = new Bootstrap();

	private static final HeartbeatClientChannelHandler heartbeatClientChannelHandler = new HeartbeatClientChannelHandler();

	public HeartbeatClientBootStrap(String address, int port) {
		this.address = address;
		this.port = port;
	}

	private static void start() throws InterruptedException {
		final EventLoopGroup group = new NioEventLoopGroup();

		bootstrap.group(group).remoteAddress(new InetSocketAddress(address, port)).channel(NioSocketChannel.class)
				.option(ChannelOption.SO_KEEPALIVE, true).handler(new ChannelInitializer<SocketChannel>() {
					@Override
					protected void initChannel(SocketChannel ch) throws Exception {
						ChannelPipeline pipeline = ch.pipeline();
						pipeline.addLast(new IdleStateHandler(20, 10, 0));
						pipeline.addLast(new ObjectDecoder(ClassResolvers.cacheDisabled(null)));
						pipeline.addLast(new ObjectEncoder());
						pipeline.addLast(heartbeatClientChannelHandler);
					}
				});
		connect();
	}

	public static void connect() throws InterruptedException {
		if (reconnectCount > 0) {
			System.out.println("发起重连:" + delay * reconnectCount + "s");
			TimeUnit.SECONDS.sleep(delay * reconnectCount);
		}
		ChannelFuture future = bootstrap.connect();
		future.addListener(new ChannelFutureListener() {
			@Override
			public void operationComplete(ChannelFuture future) throws Exception {
				if (future.isSuccess()) {
					socketChannel = (SocketChannel) future.channel();
					isPrepared = true;
					if (reconnectCount > 0)
						System.out.println("重新连接成功:" + reconnectCount + "次");
					else
						System.out.println("连接成功");
					reconnectCount = 1;
				}
				if (future.cause() != null) {
					heartbeatClientChannelHandler.startTime = -1;
					heartbeatClientChannelHandler.println("连接失败:" + future.cause());
					reconnectCount++;
				}
			}
		});
	}

	public static void main(String[] args) throws InterruptedException {
		// 初始化地址信息
		ClientIdentity clientIdentity = new ClientIdentity("001", address + ":" + 9001);
		HeartbeatClientBootStrap bootStrap = new HeartbeatClientBootStrap("127.0.0.1", 8080);
		start();
		int i = 0;
		// 在环境没有准备好之前，如果不阻塞，则会导致继续往下执行，调用发送心跳的代码，然后handler会捕获到异常，会多次重复调用connect方法
		while (!isPrepared) {
			TimeUnit.SECONDS.sleep(delay * (reconnectCount - 1));
		}
		while (i++ < 200) {
			TimeUnit.SECONDS.sleep(5);
			AKSMessage message = new AKSMessage();
			socketChannel.writeAndFlush(message);
			System.out.println("尝试发送aks:" + i);
		}
	}
}
