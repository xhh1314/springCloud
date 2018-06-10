package com.example.netty.heartcheck.server;

import com.example.netty.heartcheck.constant.MessageType;
import com.example.netty.heartcheck.message.AbsMessage;
import com.example.netty.heartcheck.message.LoginMessage;
import com.example.netty.heartcheck.message.MessageBody;
import com.example.netty.heartcheck.message.ReplyMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;

public class HeartbeatCheckServerChannelHandler extends SimpleChannelInboundHandler<AbsMessage> {

	private final ClientApplicationMap clientMap = new ClientApplicationMap();

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, AbsMessage msg) throws Exception {
		String clientId = null;
		if (MessageType.LOGIN.equals(msg.getMsgType())) {
			clientMap.saveClient(msg.getClient());
			clientMap.saveChannel(msg.getClient().getClientId(), (SocketChannel) ctx.channel());
		} else {
			// 没有客户端信息，则告诉客户端重新注册
			if (msg.getClient() == null) {
				LoginMessage message = new LoginMessage();
				System.out.println("应该重新注册");
				ctx.channel().writeAndFlush(message);
				return;
			}

			if (msg.getClient().getClientId() == null) {
				System.out.println("clientId为空!");
				ctx.channel().write(new LoginMessage());
				return;
			}
			clientId = msg.getClient().getClientId();
			if (clientMap.getChannel(clientId) == null) {
				LoginMessage message = new LoginMessage();
				System.out.println("应该重新注册");
				ctx.channel().writeAndFlush(message);
				return;
			}

		}

		switch (msg.getMsgType()) {
		case AKS: {
			ReplyMessage message = new ReplyMessage();
			MessageBody body = new MessageBody("回复AKS,注册中心地址:localhost:2008,注册中心状态良好!");
			message.setBody(body);
			clientMap.getChannel(clientId).writeAndFlush(message);
		}
			break;
		case PING: {
			ReplyMessage message = new ReplyMessage();
			MessageBody body = new MessageBody("回复ping,注册中心地址:localhost:2008,注册中心状态良好!");
			message.setBody(body);
			clientMap.getChannel(clientId).writeAndFlush(message);
		}
			break;
		case REPLY: {
			ReplyMessage message = (ReplyMessage) msg;
			System.out.println("收到客户端回复," + msg.getClient() + ":" + message.getBody());
		}
		default:
			break;

		}

	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		String clientId = clientMap.getClientIdByChannel((SocketChannel) ctx.channel());
		if (clientId != null) {
			clientMap.removeAddress(clientId);
		}
		clientMap.removeChannel((SocketChannel) ctx.channel());
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
}
