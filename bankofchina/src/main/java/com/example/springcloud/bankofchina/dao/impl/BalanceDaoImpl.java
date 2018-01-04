package com.example.springcloud.bankofchina.dao.impl;

import com.example.springcloud.bankofchina.dao.BalanceDao;
import com.example.springcloud.bankofchina.entity.BalanceDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class BalanceDaoImpl implements BalanceDao {
    @Autowired
    private BalanceJPA balanceJPA;
    @Override
    public BalanceDO save(BalanceDO balanceDO) {
        return balanceJPA.save(balanceDO);
        //return null;
    }

    @Override
    public BalanceDO getBalanceById(Integer id) {
        return balanceJPA.getBalanceDOByBalanceId(id);
    }

    @Override
    public Integer updateBalance(BalanceDO balanceDO) {
        BalanceDO old=getBalanceById(balanceDO.getBalanceId());
        old.setAmount(balanceDO.getAmount());
        return balanceJPA.save(old)!=null?1:0;
    }


}
