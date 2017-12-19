package com.example.springcloud.bankofchina.controller;

import com.example.springcloud.bankofchina.entity.BalanceDO;
import com.example.springcloud.bankofchina.manage.Restful;
import com.example.springcloud.bankofchina.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller(value="/balance")
@ResponseBody
public class BalanceController {
    @Autowired
    private BalanceService balanceService;

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
     * @param id 账户id
     * @param number 转账金额
     * @return
     */
    @RequestMapping(value="/transferMoneyToICBC")
    public Restful transferMoneyToICBC(@RequestParam("id") Integer id, @RequestParam("number") Double number){
        if(id==null || number==null || number==0)
            return Restful.failure("不合法的参数！");
        BalanceDO balanceDO=new BalanceDO();
        balanceDO.setAmount(number);
        balanceDO.setBalanceId(id);
        return balanceService.transferMoneyToICBC(balanceDO);
    }


}
