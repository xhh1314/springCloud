package com.example.springcloud.bankofchina.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "balance")
public class BalanceDO {

    private Integer balanceId;
    private Double amount;


    @Id
    @GeneratedValue
    @Column(name = "balance_id")
    public Integer getBalanceId() {
        return balanceId;
    }

    public void setBalanceId(Integer balanceId) {
        this.balanceId = balanceId;
    }

    @Column(name = "amount")
    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
