package com.example.springcloud.icbc.manage;

import com.example.springcloud.icbc.dao.BalanceDao;
import com.example.springcloud.icbc.entity.BalanceDO;
import com.example.springcloud.icbc.util.BeanMapUtil;
import com.example.springcloud.icbc.vo.BalanceVO;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.Map;

/**
 * 先假设redis是单点的情况实现逻辑
 *
 * @author lh
 * @date 2018/1/9
 * @since
 */
@Service
public class BalanceManageRedisImpl implements BalanceManage {
    private final String host = "10.1.11.28";
    private final int port = 6379;
    private final GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
    private final JedisPool jedisPool = new JedisPool(poolConfig, host, port);
    @Autowired
    private BalanceDao balanceDao;

    @Override
    public BalanceVO saveBalance(BalanceVO balanceVO) {
        BalanceDO balanceDO = balanceDao.save(new BalanceDO(balanceVO));
        String key = "icbc:balance:" + balanceDO.getBalanceId();
        Map<String, String> map = new HashMap<>(4);
        map.put("balanceId", balanceDO.getBalanceId().toString());
        map.put("amount", balanceDO.getAmount().toString());
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.hmset(key, map);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null)
                jedis.close();
        }
        return new BalanceVO(balanceDO);
    }

    @Override
    public BalanceVO getBalanceById(Integer id) {
        String key = "icbc:balance:" + id.toString();
        BalanceVO balanceVO = null;
        Jedis conn = null;
        try {
            conn = jedisPool.getResource();
            Map<String, String> hashMap = conn.hgetAll(key);
            if (hashMap!=null&&!hashMap.isEmpty()) {
                balanceVO = new BalanceVO();
                balanceVO.setBalanceId(Integer.parseInt(hashMap.get("balanceId")));
                balanceVO.setAmount(Double.parseDouble(hashMap.get("amount")));
            } else {
                balanceVO = new BalanceVO(balanceDao.getBalanceById(id));
                hashMap.put("balanceId", balanceVO.getBalanceId().toString());
                hashMap.put("amount", balanceVO.getAmount().toString());
                conn.hmset(key, hashMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
        return balanceVO;
    }

    @Override
    public BalanceVO updateBalance(BalanceVO balanceVO) {
        balanceDao.updateBalance(new BalanceDO(balanceVO));
        String key = "icbc:balance:" + balanceVO.getBalanceId();
        HashMap<String, String> hashMap = new HashMap<>(4);
        hashMap.put("balanceId", balanceVO.getBalanceId().toString());
        hashMap.put("amount", balanceVO.getAmount().toString());
        Jedis conn = null;
        try {
            conn = jedisPool.getResource();
            conn.hmset(key, hashMap);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null)
                conn.close();
        }
        return balanceVO;
    }
}
