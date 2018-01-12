package com.example.springcloud.icbc.manage;

import com.example.springcloud.icbc.dao.BalanceDao;
import com.example.springcloud.icbc.entity.BalanceDO;
import com.example.springcloud.icbc.vo.BalanceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;


@CacheConfig(cacheNames = "balanceCache")
@Transactional
public class BalanceManageImpl implements BalanceManage {
    @Autowired
    private BalanceDao balanceDao;
    @Override
    @CacheEvict(key = "#balanceVO.balanceId")
    public BalanceVO saveBalance(BalanceVO balanceVO) {

        return new BalanceVO(balanceDao.save(new BalanceDO(balanceVO)));
    }

    @Override
    @Cacheable(key = "#balanceId")
    public BalanceVO getBalanceById(Integer balanceId) {
        BalanceDO balanceDO=balanceDao.getBalanceById(balanceId);
        if(balanceDO==null)
            return null;
        return new BalanceVO(balanceDO);
    }

    @Override
    @CacheEvict(key = "#balanceVO.balanceId")
    public BalanceVO updateBalance(BalanceVO balanceVO) {
        balanceDao.updateBalance(new BalanceDO(balanceVO));
        return this.getBalanceById(balanceVO.getBalanceId());
    }
}
