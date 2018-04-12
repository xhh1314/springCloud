package com.example.taobao.rpc.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.example.taobao.rpc.service.UserService;
import org.springframework.stereotype.Component;


@Service(timeout = 5000,interfaceClass = UserService.class)
@Component
public class UserServiceImpl  implements UserService {
    @Override
    public String getUserByName(String name) {
        String result="the name is :"+name;
        return result;
    }
}
