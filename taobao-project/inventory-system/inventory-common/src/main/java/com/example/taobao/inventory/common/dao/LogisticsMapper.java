package com.example.taobao.inventory.common.dao;

import com.example.taobao.inventory.common.entity.LogisticsDO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface LogisticsMapper {

	//@Transactional
	Integer saveLogistics(LogisticsDO logisticsDO);

	//@Transactional
	Integer updateLogistics(LogisticsDO logisticsDO);

	//@Transactional(readOnly = true)
	LogisticsDO getLogisticsByOrderId(Integer orderId);

	//@Transactional(readOnly = true)
	LogisticsDO getLogisticsById(Integer id);

}
