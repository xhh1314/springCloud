package com.example.taobao.order.webservice.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.example.taobao.order.api.OrderFindServiceTest;
import com.example.taobao.order.dto.OrderDTO;

@Service(version = "1.0.0", application = "${dubbo.application.id}", protocol = "${dubbo.protocol.id}", registry = "${dubbo.registry.id}")
public class OrderFindServiceTestImpl implements OrderFindServiceTest {
	@Override
	public OrderDTO getOrderDetail(Long id) {
		return null;
	}
}
