package com.example.netty.heartcheck.client;

import com.example.netty.heartcheck.constant.MessageType;
import com.example.netty.heartcheck.message.*;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

@ChannelHandler.Sharable
public class HeartbeatClientChannelHandler extends SimpleChannelInboundHandler<AbsMessage> {

	long startTime = -1;

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		if (evt instanceof IdleState) {
			IdleStateEvent event = (IdleStateEvent) evt;
			switch (event.state()) {
			case WRITER_IDLE: {
				PingMessage message = new PingMessage();
				ctx.channel().writeAndFlush(message);
				System.out.println("向注册中心发送心跳!");
			}
				break;
			default:
				break;

			}

		}
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, AbsMessage msg) throws Exception {

		MessageType type = msg.getMsgType();
		switch (msg.getMsgType()) {
		case LOGIN: {
			LoginMessage loginMessage = new LoginMessage();
			ctx.channel().writeAndFlush(loginMessage);
		}
			break;
		case AKS: {
			ReplyMessage message = new ReplyMessage();
			MessageBody body = new MessageBody("客户端信息:" + message.getClient());
			message.setBody(body);
			ctx.channel().writeAndFlush(message);
		}
			break;
		case PING: {
			System.out.println("收到注册中心ping请求!");
		}
			break;
		case REPLY: {
			ReplyMessage message = (ReplyMessage) msg;
			System.out.println("收到注册中心回复:" + message.getBody());
		}
			break;
		default:
			break;

		}

	}

	@Override
	public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
		HeartbeatClientBootStrap.connect();
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		super.channelInactive(ctx);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

	void println(String msg) {
		if (startTime < 0) {
			System.err.format("[SERVER IS DOWN] %s%n", msg);
		} else {
			System.err.format("[UPTIME: %5ds] %s%n", (System.currentTimeMillis() - startTime) / 1000, msg);
		}
	}
}
