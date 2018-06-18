package com.example.springcloud.servicefeign.controller;

import com.example.springcloud.servicefeign.service.SchedualServiceHiFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HiController {
    @Autowired
    @Qualifier(value = "service-hi-api")
    SchedualServiceHiFeign schedualServiceHiFeign;

    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    public String sayHi(@RequestParam("name") String name) {
        return schedualServiceHiFeign.sayHiFromClientOne(name);
    }
}
