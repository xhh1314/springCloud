package com.example.myoauth.server.controller;

import com.example.base.rest.Rest;
import com.example.myoauth.server.service.AccessTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/oauth")
public class AccessTokenController {

    @Autowired
    private AccessTokenService accessTokenService;

    @RequestMapping(value = "/access_token", method = RequestMethod.POST)
    @ResponseBody
    public Rest getAccessToken(String key, String secret) {
        if (!StringUtils.hasText(key))
            return Rest.failure(403, "required param of key ");
        if (!StringUtils.hasText(secret))
            return Rest.failure(403, "required param of secret");
        return accessTokenService.createAccessToken(key, secret);
    }

    @RequestMapping(value = "/token",method = RequestMethod.POST)
    public Rest getRefreshToken(String clientKey,String refreshToken){

        return Rest.success("");
    }
}
