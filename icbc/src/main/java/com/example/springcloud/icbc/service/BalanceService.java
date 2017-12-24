package com.example.springcloud.icbc.service;

import com.example.springcloud.bankofchina.manage.Restful;
import com.example.springcloud.icbc.dao.BalanceDao;
import com.example.springcloud.icbc.entity.BalanceDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;


/**
 *转账服务
 *@author lh
 *@date 2017/12/19
 *@since
 */
@Service
public class BalanceService {
    private static final Logger log= LoggerFactory.getLogger(BalanceService.class);
    @Autowired
    private BalanceDao balanceDao;

    /**
     *减少账户余额
     *@date 2017/12/19
     *@param
     *@author lh
     *@since
     */
    @Transactional(rollbackFor = {Exception.class})
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
    @Transactional(rollbackFor = {Exception.class})
    public Restful increaseAmount(Integer id,double number){
        BalanceDO oldBalance = balanceDao.getBalanceById(id);
        if (oldBalance == null)
            return Restful.failure("账户不存在");
        BigDecimal origin = new BigDecimal(oldBalance.getAmount());
        BigDecimal increaseNumber = new BigDecimal(number);
        oldBalance.setAmount(origin.add(increaseNumber).doubleValue());
        balanceDao.save(oldBalance);
      //  log.info("账户:{}成功增加金额:{}",id,number);
        return Restful.success();
    }

}
