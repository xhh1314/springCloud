package com.example.myoauth.dto;

import java.util.Date;

public class AccessTokenDTO implements Cloneable{

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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AccessTokenDTO{");
        sb.append("tokenId=").append(tokenId);
        sb.append(", token='").append(token).append('\'');
        sb.append(", refreshTokenId=").append(refreshTokenId);
        sb.append(", clientId=").append(clientKey);
        sb.append(", createTime=").append(createTime);
        sb.append('}');
        return sb.toString();
    }
}
