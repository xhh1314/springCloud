package com.example.myoauth.server.controller;

import com.example.base.rest.Rest;
import com.example.myoauth.common.constant.StatusConstans;
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

    /**
     * 获取accessToken 和 refreshToken
     *
     * @param key
     * @param secret
     * @return
     */
    @RequestMapping(value = "/access_token", method = RequestMethod.POST)
    @ResponseBody
    public Rest getAccessToken(String key, String secret) {
        if (!StringUtils.hasText(key))
            return Rest.failure(StatusConstans.AUTHENTICATION_ERROR, "required param of key ");
        if (!StringUtils.hasText(secret))
            return Rest.failure(StatusConstans.AUTHENTICATION_ERROR, "required param of secret");
        return accessTokenService.createAccessToken(key, secret);
    }

    /**
     * 刷新accessToken
     *
     * @param client_key
     * @param refresh_token
     * @return
     */
    @RequestMapping(value = "/refresh_token", method = RequestMethod.POST)
    @ResponseBody
    public Rest getRefreshToken(String client_key, String refresh_token) {
        if (!StringUtils.hasText(client_key))
            return Rest.failure(StatusConstans.AUTHENTICATION_ERROR, "required param of  key");
        if (!StringUtils.hasText(refresh_token))
            return Rest.failure(StatusConstans.AUTHENTICATION_ERROR, "required param of refreshToken");
        Rest rest = accessTokenService.refreshToken(client_key, refresh_token);
        return rest;
    }

    /**
     * 检查accessToken是否合法
     * 如果还有资源限制，应该修改实现
     *
     * @param access_token
     * @return
     */
    @RequestMapping(value = "/check_access_token", method = RequestMethod.GET)
    @ResponseBody
    public Rest checkAccessToken(String access_token) {
        if (!StringUtils.hasText(access_token))
            return Rest.failure(StatusConstans.AUTHENTICATION_ERROR, "required param of accessToken");
        return accessTokenService.checkAccessToken(access_token);

    }
}
