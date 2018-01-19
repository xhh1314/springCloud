package com.example.springcloud.icbc.dao.impl;

import com.example.springcloud.icbc.entity.BalanceDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BalanceJPA extends JpaRepository<BalanceDO, Integer> {

    @Query(value = "select * from balance where balance_id=?1 for update",nativeQuery = true)
    BalanceDO getBalanceById(Integer id);
}
