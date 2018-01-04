package com.example.springcloud.bankofchina.service;

import com.example.springcloud.bankofchina.dao.BalanceDao;
import com.example.springcloud.bankofchina.entity.BalanceDO;
import com.example.springcloud.bankofchina.feign.IcbcFeign;
import com.example.springcloud.bankofchina.manage.Restful;
import com.example.springcloud.bankofchina.manage.TransferProducer;
import com.sun.org.apache.regexp.internal.RE;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.SQLException;


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
    @Autowired
    private TransferProducer transferProducer;

    /**
     * 减少账户余额
     *
     * @param
     * @date 2017/12/19
     * @author lh
     * @since
     */
    @Transactional(rollbackFor = {SQLException.class,Exception.class})
    public Restful decreaseAmount(Integer id, double number) {
        BalanceDO oldBalance = balanceDao.getBalanceById(id);
        if (oldBalance == null || oldBalance.getBalanceId()==null)
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
     * 增加账户余额
     *
     * @param
     * @date 2017/12/19
     * @author lh
     * @since
     */
    @Transactional(rollbackFor = {SQLException.class,Exception.class})
    public Restful increaseAmount(Integer id, double number) {
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
     *
     * @param balanceDO
     * @return
     */
    @Transactional(rollbackFor = {SQLException.class,Exception.class})
    public Restful transferMoneyToICBC(BalanceDO balanceDO) throws InterruptedException, RemotingException, MQClientException, MQBrokerException, UnsupportedEncodingException {
        boolean saveFlag = (this.decreaseAmount(balanceDO.getBalanceId(), balanceDO.getAmount())).getMeta().isSuccess();
        boolean sendMessageFlag=transferProducer.sendTransferMessage("icbc",balanceDO);
        if(!sendMessageFlag){
            throw new RuntimeException("发送消息失败，出现异常！");
        }
        return saveFlag&&sendMessageFlag?Restful.success("账户"+balanceDO.getBalanceId()+"转账成功！"):Restful.failure("账户"+balanceDO.getBalanceId()+"转账失败!");
    }
}
