package com.example.springcloud.icbc.entity;

import com.example.springcloud.icbc.vo.BalanceVO;

import javax.persistence.*;

@Entity
@Table(name = "balance")
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

    @Override
    public String toString() {
        return "BalanceDO{" +
                "balanceId=" + balanceId +
                ", amount=" + amount +
                '}';
    }

    public BalanceDO(){}
    public BalanceDO(BalanceVO vo){
        this.balanceId=vo.getBalanceId();
        this.amount=vo.getAmount();
    }
}
