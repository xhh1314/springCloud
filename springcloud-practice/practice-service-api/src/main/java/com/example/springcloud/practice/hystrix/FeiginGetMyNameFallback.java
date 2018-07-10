package com.example.springcloud.practice.hystrix;

import com.example.springcloud.practice.api.FeignGetMyName;
import org.springframework.stereotype.Component;

@Component
public class FeiginGetMyNameFallback implements FeignGetMyName {
    @Override
    public String getMyName(String name) {
        return "-----------default name---------";
    }
}
