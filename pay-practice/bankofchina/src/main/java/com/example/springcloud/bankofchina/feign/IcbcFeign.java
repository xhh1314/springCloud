package com.example.springcloud.bankofchina.feign;

import com.example.springcloud.bankofchina.entity.BalanceDO;
import com.example.springcloud.bankofchina.manage.Restful;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "icbc")
public interface IcbcFeign {

    @RequestMapping(value="/balance/decrease",method = RequestMethod.PUT)
    Restful decreaseAmount(@RequestBody BalanceDO balanceDO);

    @RequestMapping(value="/balance/increase",method = RequestMethod.PUT)
    Restful increaseAmount(@RequestBody BalanceDO balanceDO);

}
