package com.example.springcloud.icbc.vo;

import com.example.springcloud.icbc.entity.BalanceDO;

public class BalanceVO {
    private  Integer balanceId;
    private Double amount;

    public Integer getBalanceId() {
        return balanceId;
    }

    public void setBalanceId(Integer balanceId) {
        this.balanceId = balanceId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
    public BalanceVO(){}

    public BalanceVO(BalanceDO balanceDO){
        this.balanceId=balanceDO.getBalanceId();
        this.amount=balanceDO.getAmount();

    }
}
