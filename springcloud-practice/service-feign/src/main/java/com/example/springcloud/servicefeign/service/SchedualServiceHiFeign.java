package com.example.springcloud.servicefeign.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "webservice-hi",fallback = SchedualServiceHiHystric.class,qualifier = "service-hi-api")
public interface SchedualServiceHiFeign {
    @RequestMapping(value="/hi",method = RequestMethod.GET)
    String sayHiFromClientOne(@RequestParam("name") String name);
}
