package com.example.springcloud.bankofchina.feign;

import org.example.news.base.common.restful.Rest;
import org.example.news.sns.dto.SnsSubjectDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "NEWS-SNS-WEBSERVICE")
public interface FeignSns {

    @RequestMapping(value = "/sns/subject/testDateFormat",method = RequestMethod.GET)
    Rest testDateFormat(@RequestBody SnsSubjectDTO subjectDTO);
}
