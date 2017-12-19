package com.example.springcloud.bankofchina.service;

import com.example.springcloud.bankofchina.dao.BalanceDao;
import com.example.springcloud.bankofchina.entity.BalanceDO;
import com.example.springcloud.bankofchina.feign.IcbcFeign;
import com.example.springcloud.bankofchina.manage.Restful;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
/**
 *转账服务
 *@author lh
 *@date 2017/12/19
 *@since
 */
public class BalanceService {
    @Autowired
    private BalanceDao balanceDao;
    @Autowired
    private IcbcFeign icbcFeign;

    /**
     *减少账户余额
     *@date 2017/12/19
     *@param
     *@author lh
     *@since
     */
    public Restful decreaseAmount(Integer id, double number) {
        BalanceDO oldBalance = balanceDao.getBalanceById(id);
        if (oldBalance == null)
            return Restful.failure("账户不存在");
        if (number > oldBalance.getAmount())
            return Restful.failure("账户余额不足");
        BigDecimal origin = new BigDecimal(oldBalance.getAmount());
        BigDecimal decreaseNumber = new BigDecimal(number);
        oldBalance.setAmount(origin.subtract(decreaseNumber).doubleValue());
        balanceDao.save(oldBalance);
        return Restful.success();
    }
    /**
     *增加账户余额
     *@date 2017/12/19
     *@param
     *@author lh
     *@since
     */
    public Restful increaseAmount(Integer id,double number){
        BalanceDO oldBalance = balanceDao.getBalanceById(id);
        if (oldBalance == null)
            return Restful.failure("账户不存在");
        BigDecimal origin = new BigDecimal(oldBalance.getAmount());
        BigDecimal increaseNumber = new BigDecimal(number);
        oldBalance.setAmount(origin.add(increaseNumber).doubleValue());
        balanceDao.save(oldBalance);
        return Restful.success();
    }

    /**
     * 转账到工商银行的服务
     * @param balanceDO
     * @return
     */
    public Restful transferMoneyToICBC(BalanceDO balanceDO) {
        Restful rest=null;
        if(!(rest=this.decreaseAmount(balanceDO.getBalanceId(),balanceDO.getAmount())).getMeta().isSuccess())
            return rest;
        return rest=icbcFeign.increaseAmount(balanceDO);
    }
}
