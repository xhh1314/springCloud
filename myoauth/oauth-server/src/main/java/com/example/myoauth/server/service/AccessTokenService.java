package com.example.myoauth.server.service;

import com.example.myoauth.common.entity.AccessTokenDO;
import com.example.myoauth.dto.AccessTokenDTO;

public interface AccessTokenService {

    AccessTokenDTO  createAccessToken(String clientKey,String clientSecret);


}
