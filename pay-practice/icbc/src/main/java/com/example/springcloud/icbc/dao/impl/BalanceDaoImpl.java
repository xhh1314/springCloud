package com.example.springcloud.icbc.dao.impl;

import com.example.springcloud.icbc.dao.BalanceDao;
import com.example.springcloud.icbc.entity.BalanceDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BalanceDaoImpl implements BalanceDao{
    @Autowired
    private BalanceJPA balanceJPA;
    @Override
    public BalanceDO save(BalanceDO balanceDO) {
        return balanceJPA.save(balanceDO);
    }

    @Override
    public BalanceDO getBalanceById(Integer id) {
        return balanceJPA.getOne(id);
    }

    @Override
    public Integer updateBalance(BalanceDO balanceDO) {
        BalanceDO old=getBalanceById(balanceDO.getBalanceId());
        old.setAmount(balanceDO.getAmount());
        return balanceJPA.save(old)!=null?1:0;
    }

}
