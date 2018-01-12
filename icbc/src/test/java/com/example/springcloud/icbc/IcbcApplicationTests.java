package com.example.springcloud.icbc;

import com.example.springcloud.icbc.dao.BalanceDao;
import com.example.springcloud.icbc.entity.BalanceDO;
import com.example.springcloud.icbc.manage.BalanceManage;
import com.example.springcloud.icbc.service.BalanceService;
import com.example.springcloud.icbc.vo.BalanceVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = IcbcApplication.class)
public class IcbcApplicationTests {
    @Autowired
    private BalanceDao balanceDao;

    @Autowired
    private BalanceService balanceService;
    @Autowired
    private BalanceManage balanceManage;

    @Test
    public void contextLoads() {
    }

    @Test
    public void insertTest() {
        BalanceDO balanceDO = new BalanceDO();
        balanceDO.setAmount(2000d);
        balanceDao.save(balanceDO);
    }

    @Test
    public void incrementAmountTest() {
        balanceService.increaseAmount(1,2d);

    }
    @Test
    public void getBalanceById(){
        BalanceVO balanceVO1 = new BalanceVO();
        balanceVO1.setBalanceId(1);
        balanceVO1.setAmount(2000d);
        balanceManage.saveBalance(balanceVO1);
        BalanceVO balanceVO=balanceManage.getBalanceById(1);
        balanceManage.getBalanceById(1);
        balanceManage.getBalanceById(1);
        balanceManage.saveBalance(balanceVO);
        balanceManage.getBalanceById(1);
        balanceManage.getBalanceById(1);
    }

}
