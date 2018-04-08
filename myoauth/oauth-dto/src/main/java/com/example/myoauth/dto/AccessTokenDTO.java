package com.example.myoauth.dto;

import java.util.Date;

public class AccessTokenDTO implements Cloneable{

    private String accessToken;
    private Integer refreshTokenId;
    private String clientKey;
    private Date createTime;
    private Integer expiresTime;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
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
        sb.append(", accessToken='").append(accessToken).append('\'');
        sb.append(", refreshTokenId=").append(refreshTokenId);
        sb.append(", clientId=").append(clientKey);
        sb.append(", createTime=").append(createTime);
        sb.append('}');
        return sb.toString();
    }
}
