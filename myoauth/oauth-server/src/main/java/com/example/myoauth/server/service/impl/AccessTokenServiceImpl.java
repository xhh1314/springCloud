package com.example.myoauth.server.service.impl;

import com.example.myoauth.common.manage.AccessTokenManage;
import com.example.myoauth.dto.AccessTokenDTO;
import com.example.myoauth.server.service.AccessTokenService;
import org.springframework.beans.factory.annotation.Autowired;

public class AccessTokenServiceImpl  implements AccessTokenService {

    @Autowired
    private AccessTokenManage accessTokenManage;
    @Override
    public AccessTokenDTO createAccessToken(String clientKey, String clientSecret) {


        return null;
    }
}
