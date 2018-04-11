package com.example.springcloud.bankofchina.dao;

import com.example.springcloud.bankofchina.entity.BalanceDO;

public interface BalanceDao {

    BalanceDO save(BalanceDO balanceDO);
    BalanceDO getBalanceById(Integer id);
    Integer   updateBalance(BalanceDO balanceDO);

}
