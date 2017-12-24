package com.example.springcloud.bankofchina.controller;

import com.example.springcloud.bankofchina.entity.BalanceDO;
import com.example.springcloud.bankofchina.manage.Restful;
import com.example.springcloud.bankofchina.service.BalanceService;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping(value="/balance")
@ResponseBody
public class BalanceController {
    @Autowired
    private BalanceService balanceService;
    private static final Logger log= LoggerFactory.getLogger(BalanceController.class);

    @RequestMapping(value = "/decrease",method = RequestMethod.PUT)
    public Restful decreaseNumber(@RequestBody BalanceDO balanceDO){
        if(balanceDO==null)
            return Restful.failure("账户信息缺失！");
        if(balanceDO.getAmount()==0 || balanceDO.getAmount()==null)
            return Restful.failure("金额不能为空!");
        if(balanceDO.getBalanceId()==null){
            return Restful.failure("账户id不能为空！");
        }
        return balanceService.decreaseAmount(balanceDO.getBalanceId(),balanceDO.getAmount());
    }
    @RequestMapping(value = "/increase",method = RequestMethod.PUT)
    public Restful increaseNumber(@RequestBody BalanceDO balanceDO){
        if(balanceDO==null)
            return Restful.failure("账户信息缺失！");
        if(balanceDO.getAmount()==0 || balanceDO.getAmount()==null)
            return Restful.failure("金额不能为空!");
        if(balanceDO.getBalanceId()==null){
            return Restful.failure("账户id不能为空！");
        }
        return balanceService.increaseAmount(balanceDO.getBalanceId(),balanceDO.getAmount());
    }

    /**
     * 转账到工商银行
     * @return
     */
    @RequestMapping(value="/transferMoneyToICBC")
    public Restful transferMoneyToICBC(HttpServletRequest request){
        Integer id=Integer.valueOf(request.getParameter("id"));
        Double number=Double.valueOf(request.getParameter("number"));
        if(id==null || number==null || number==0)
            return Restful.failure("不合法的参数！");
        BalanceDO balanceDO=new BalanceDO();
        balanceDO.setAmount(number);
        balanceDO.setBalanceId(id);
        Restful rest=null;
        try {
            rest= balanceService.transferMoneyToICBC(balanceDO);
        } catch (InterruptedException e) {
           log.error("转账发送错误!{}",e);
           rest=Restful.failure(e,"转账发生异常！");
        } catch (RemotingException e) {
            log.error("转账发送错误!{}",e);
            rest=Restful.failure(e,"转账发生异常！");
        } catch (MQClientException e) {
            log.error("转账发送错误!{}",e);
            rest=Restful.failure(e,"转账发生异常！");
        } catch (MQBrokerException e) {
            log.error("转账发送错误!{}",e);
            rest=Restful.failure(e,"转账发生异常！");
        } catch (UnsupportedEncodingException e) {
            log.error("转账发送错误!{}",e);
            rest=Restful.failure(e,"转账发生异常！");
        }

        return rest;
    }


}
