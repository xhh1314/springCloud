package com.example.netty.heartcheck.server;

import com.example.netty.heartcheck.constant.ClientInfo;
import io.netty.channel.socket.SocketChannel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 存放注册过来的客户端信息
 *
 * @author lh
 * @date 2018/5/12
 * @since
 */
public class ClientApplicationMap {

	/**
	 * 存放客户端地址的容器
	 */
	private final Map<String, List<ClientInfo>> clientMap = new ConcurrentHashMap<String, List<ClientInfo>>(16);

	private final Map<String, SocketChannel> socketChannelMap = new ConcurrentHashMap<>(32);

	public void saveClient(ClientInfo client) {
		List<ClientInfo> address = clientMap.get(client.getClientId());
		if (address == null) {
			synchronized (client.getClientId()) {
				if (address == null) {
					address = new ArrayList<>(8);
					address.add(client);
				}
			}
		}
		clientMap.put(client.getClientId(), address);

	}

	public List<ClientInfo> getAddress(String clientId) {
		return clientMap.get(clientId);
	}

	public void removeAddress(String clientId) {
		List<ClientInfo> address = clientMap.get(clientId);
		if (address == null || address.size() == 0)
			return;
		Iterator<ClientInfo> iterator = address.iterator();
		while (iterator.hasNext()) {
			ClientInfo clientInfo = iterator.next();
			if (clientId.equals(clientInfo.getClientId())) {
				iterator.remove();
				break;
			}
		}
	}

	public void saveChannel(String clientId, SocketChannel socketChannel) {
		socketChannelMap.put(clientId, socketChannel);
	}

	public SocketChannel getChannel(String clientId) {

		return socketChannelMap.get(clientId);
	}

	public void removeChannel(SocketChannel socketChannel) {
		for (Map.Entry entry : socketChannelMap.entrySet()) {
			if (entry.getValue() == socketChannel) {
				socketChannelMap.remove(entry.getKey());
			}
		}
	}

	public String getClientIdByChannel(SocketChannel socketChannel) {
		for (Map.Entry<String, SocketChannel> entry : socketChannelMap.entrySet()) {
			if (entry.getValue() == socketChannel) {
				return entry.getKey();
			}
		}

		return null;
	}
}
