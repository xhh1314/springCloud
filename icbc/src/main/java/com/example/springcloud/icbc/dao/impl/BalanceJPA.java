package com.example.springcloud.icbc.dao.impl;

import com.example.springcloud.icbc.entity.BalanceDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BalanceJPA extends JpaRepository<BalanceDO, Integer> {

    @Query(value = "from BalanceDO where balanceId=?1")
    BalanceDO getBalanceById(Integer id);
}
