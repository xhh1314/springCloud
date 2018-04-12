package com.example.taobao.rpc.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.example.taobao.rpc.service.UserService;
import org.springframework.stereotype.Component;


@Service(
        version = "1.0.0",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}"
)
@Component
public class UserServiceImpl  implements UserService {
    @Override
    public String getUserByName(String name) {
        String result="the name is :"+name;
        return result;
    }
}
