package com.example.springcloud.icbc;

import com.example.springcloud.icbc.dao.BalanceDao;
import com.example.springcloud.icbc.entity.BalanceDO;
import com.example.springcloud.icbc.service.BalanceService;
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

}
