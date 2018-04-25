package com.example.taobao.order.webservice.controller;

import com.example.base.rest.Rest;
import com.example.taobao.order.common.entity.ScoreDO;
import com.example.taobao.order.common.manage.ScoreMange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value = "/score")
public class ScoreController {

    @Autowired
    private ScoreMange scoreMange;

    @RequestMapping(value = "/getTopScore", method = RequestMethod.GET)
    public List<ScoreDO> getTopScore(Integer topNumber) {
        if (topNumber == null)
            return Collections.emptyList();
        return scoreMange.getTopScore(topNumber);

    }

    @RequestMapping(value = "/updateScore", method = RequestMethod.PUT)
    public Rest updateScore(ScoreDO scoreDO) {
        if (scoreDO.getScoreId() == null)
            return Rest.failure(404, "scoreId参数缺失!");
        scoreMange.updateScore(scoreDO);
        return Rest.success();
    }

}
