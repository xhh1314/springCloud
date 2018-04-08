package com.example.myoauth.common.entity;

import com.example.myoauth.dto.AccessTokenDTO;
import org.springframework.beans.BeanUtils;

import java.util.Date;

public class AccessTokenDO implements Cloneable {

    private Integer tokenId;
    private String token;
    private Integer refreshTokenId;
    private String clientKey;
    private Date createTime;
    private Integer expiresTime;

    public Integer getTokenId() {
        return tokenId;
    }

    public void setTokenId(Integer tokenId) {
        this.tokenId = tokenId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getRefreshTokenId() {
        return refreshTokenId;
    }

    public void setRefreshTokenId(Integer refreshTokenId) {
        this.refreshTokenId = refreshTokenId;
    }

    public String getClientKey() {
        return clientKey;
    }

    public void setClientKey(String clientKey) {
        this.clientKey = clientKey;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getExpiresTime() {
        return expiresTime;
    }

    public void setExpiresTime(Integer expiresTime) {
        this.expiresTime = expiresTime;
    }

    public AccessTokenDTO tranferToDTO() {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        BeanUtils.copyProperties(this, accessTokenDTO);
        return accessTokenDTO;


    }

    public AccessTokenDO tranferToDO(AccessTokenDTO accessTokenDTO) {
        BeanUtils.copyProperties(accessTokenDTO, this);
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AccessTokenDO{");
        sb.append("tokenId=").append(tokenId);
        sb.append(", token='").append(token).append('\'');
        sb.append(", refreshTokenId=").append(refreshTokenId);
        sb.append(", clientId=").append(clientKey);
        sb.append(", createTime=").append(createTime);
        sb.append('}');
        return sb.toString();
    }
}
