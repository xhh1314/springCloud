package com.example.taobao.inventory.common.dao;

import com.example.taobao.inventory.common.entity.LogisticsDO;
import org.springframework.stereotype.Repository;

@Repository
public interface LogisticsMapper {

    Integer saveLogistics(LogisticsDO logisticsDO);

    Integer updateLogistics(LogisticsDO logisticsDO);
}
