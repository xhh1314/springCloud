package com.example.springcloud.bankofchina.controller;

import com.example.springcloud.bankofchina.feign.FeignSns;
import org.example.news.base.common.restful.Rest;
import org.example.news.sns.dto.SnsSubjectDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
@RequestMapping(value = "/ucenter")
public class TestDateFormatController {

    @Autowired
    FeignSns feignSns;


    @RequestMapping(value = "/test1")
    @ResponseBody
    public Rest test1() {
        SnsSubjectDTO snsSubjectDTO = new SnsSubjectDTO();
        snsSubjectDTO.setContent("仰天长啸");
        snsSubjectDTO.setSubjectId(1L);
        snsSubjectDTO.setCreateTime(new Date());
        feignSns.testDateFormat(snsSubjectDTO);
        return Rest.success("成功!");
    }

}
