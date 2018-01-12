package com.example.springcloud.icbc.dao.impl;

import com.example.springcloud.icbc.dao.BalanceDao;
import com.example.springcloud.icbc.entity.BalanceDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Repository
public class BalanceDaoImpl implements BalanceDao{
    @Autowired
    private BalanceJPA balanceJPA;
    private static final Logger log= LoggerFactory.getLogger(BalanceDaoImpl.class);

    private final AtomicInteger count=new AtomicInteger(0);
    private Lock lock=new ReentrantLock();
    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public BalanceDO save(BalanceDO balanceDO) {
        BalanceDO balanceDO1=null;
        lock.lock();
        try {
            balanceDO1=balanceJPA.save(balanceDO);
            log.info("更新信息执行次数:{}",count.addAndGet(1));
        } finally {
            lock.unlock();
        }
        return balanceDO1;

    }

    @Override
    public BalanceDO getBalanceById(Integer id) {
        return balanceJPA.getBalanceById(id);
    }

    @Override
    public Integer updateBalance(BalanceDO balanceDO) {
        log.info("更新信息执行次数:{}",count.addAndGet(1));
        BalanceDO old=getBalanceById(balanceDO.getBalanceId());
        old.setAmount(balanceDO.getAmount());
        return balanceJPA.save(old)!=null?1:0;
    }

}
