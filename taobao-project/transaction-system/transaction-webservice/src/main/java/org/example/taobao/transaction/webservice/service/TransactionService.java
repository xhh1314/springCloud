package org.example.taobao.transaction.webservice.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.example.taobao.order.api.OrderService;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

	@Reference(version = "1.0.0")
	private OrderService orderService;

	public void transaction(Integer orderId) {
		orderService.updateOrderSuccessByOrderId(orderId);
	}
}
