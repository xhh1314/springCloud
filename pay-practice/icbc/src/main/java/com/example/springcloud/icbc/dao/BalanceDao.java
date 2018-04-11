package com.example.springcloud.icbc.dao;

import com.example.springcloud.icbc.entity.BalanceDO;

public interface BalanceDao {

    BalanceDO save(BalanceDO balanceDO);
    BalanceDO getBalanceById(Integer id);
    Integer   updateBalance(BalanceDO balanceDO);

}
