package com.example.taobao.order.common.dao;

import com.example.taobao.order.common.entity.ScoreDO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ScoreMapper {

    void updateScore(ScoreDO scoreDO);

    @Transactional(rollbackFor = {Exception.class})
    Integer insertScore(ScoreDO scoreDO);

    ScoreDO getScoreByStudentId(Integer studentId);

}
