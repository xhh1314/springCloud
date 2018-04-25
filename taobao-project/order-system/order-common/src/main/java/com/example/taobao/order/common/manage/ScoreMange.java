package com.example.taobao.order.common.manage;

import com.alibaba.fastjson.JSON;
import com.example.base.util.BeanMapper;
import com.example.taobao.order.common.dao.ScoreMapper;
import com.example.taobao.order.common.entity.ScoreDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.Tuple;

import java.util.*;

@Service
public class ScoreMange {

    @Autowired
    private ScoreMapper scoreMapper;

    @Autowired
    private JedisCluster jedisCluster;

    private String rankOfSocre = "rankOfScore";

    public void updateScore(ScoreDO scoreDO) {
        scoreMapper.updateScore(scoreDO);
        jedisCluster.zadd(rankOfSocre, scoreDO.getScore(), scoreDO.getScoreId().toString());
        jedisCluster.hmset("score:" + scoreDO.getScoreId(), BeanMapper.mapBeanToStringMap(scoreDO));
    }


    public Integer insertScore(ScoreDO scoreDO) {
        Integer result = scoreMapper.insertScore(scoreDO);
        jedisCluster.zadd(rankOfSocre, scoreDO.getScore(), scoreDO.getScoreId().toString());
        jedisCluster.hmset("score:" + scoreDO.getScoreId(), BeanMapper.mapBeanToStringMap(scoreDO));
        return result;
    }

    public ScoreDO getScoreByStudentId(Integer studentId) {

        throw new RuntimeException("未实现的方法!");

    }


    public List<ScoreDO> getTopScore(Integer topNumber) {
        if (topNumber == 0)
            return Collections.emptyList();
        Set<Tuple> tupleSet = jedisCluster.zrevrangeWithScores(rankOfSocre, 0, topNumber - 1);
        List<ScoreDO> scoreDOList = new ArrayList<>(topNumber);
        tupleSet.forEach(tuple -> {
            String scoreId = tuple.getElement();
            Map<String, String> map = jedisCluster.hgetAll(scoreId);
            if (map != null && map.size() > 0) {
                scoreDOList.add(BeanMapper.mapToBean(map, ScoreDO.class));
            }
        });

        return scoreDOList;

    }

}
