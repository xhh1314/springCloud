package com.example.myoauth.server.service;

import com.example.base.rest.Rest;
import com.example.myoauth.common.entity.AccessTokenDO;
import com.example.myoauth.dto.AccessTokenDTO;

public interface AccessTokenService {

    Rest createAccessToken(String clientKey, String clientSecret);


}
