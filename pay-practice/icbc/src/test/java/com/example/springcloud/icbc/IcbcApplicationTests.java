package com.example.springcloud.icbc;

import com.example.springcloud.icbc.dao.BalanceDao;
import com.example.springcloud.icbc.entity.BalanceDO;
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
	@Test
	public void contextLoads() {
	}
	@Test
	public void insertTest(){
		BalanceDO balanceDO=new BalanceDO();
		balanceDO.setAmount(2000d);
		balanceDao.save(balanceDO);
	}

}
