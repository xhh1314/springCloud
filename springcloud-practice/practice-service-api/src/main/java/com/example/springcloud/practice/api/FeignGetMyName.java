package com.example.springcloud.practice.api;


import com.example.springcloud.practice.hystrix.FeiginGetMyNameFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "PRACTICE-SERVICE-A",fallback = FeiginGetMyNameFallback.class)
public interface FeignGetMyName {


    @RequestMapping(value = "/test/getMyName")
    String getMyName(@RequestParam(name = "name") String name);
}
