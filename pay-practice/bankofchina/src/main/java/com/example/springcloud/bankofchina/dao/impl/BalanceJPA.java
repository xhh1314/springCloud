package com.example.springcloud.bankofchina.dao.impl;

import com.example.springcloud.bankofchina.entity.BalanceDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BalanceJPA extends JpaRepository<BalanceDO, Integer> {

}
