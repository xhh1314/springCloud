package com.example.springcloud.eurekaclient;

import com.example.springcloud.bankofchina.BankOfChinaApplication;
import com.example.springcloud.bankofchina.dao.BalanceDao;
import com.example.springcloud.bankofchina.entity.BalanceDO;
import com.example.springcloud.bankofchina.manage.Restful;
import com.example.springcloud.bankofchina.service.BalanceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.Temporal;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BankOfChinaApplication.class)
public class BankOfChinaApplicationTests {
	@Autowired
	private BalanceDao balanceDao;
	@Autowired
	private BalanceService balanceService;

	@Test
	public void contextLoads() {
	}

	@Test
	public void insertTest(){
		BalanceDO balanceDO=new BalanceDO();
		balanceDO.setAmount(1000d);
		balanceDao.save(balanceDO);

	}
	@Test
	public void transfertoIcbcTest(){
		BalanceDO balanceDO=new BalanceDO();
		balanceDO.setBalanceId(1);
		balanceDO.setAmount(10d);
		Restful rest= balanceService.transferMoneyToICBC(balanceDO);
		System.out.println(rest.getMeta().getMessage());
	}

}
