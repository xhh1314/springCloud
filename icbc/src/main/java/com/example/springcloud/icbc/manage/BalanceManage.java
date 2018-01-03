package com.example.springcloud.icbc.manage;


import com.example.springcloud.icbc.vo.BalanceVO;

public interface BalanceManage {

  BalanceVO saveBalance(BalanceVO balanceVO);

  BalanceVO getBalanceById(Integer id);

  BalanceVO updateBalance(BalanceVO balanceVO);


}
