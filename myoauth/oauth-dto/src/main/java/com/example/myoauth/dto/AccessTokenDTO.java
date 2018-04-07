package com.example.myoauth.dto;

import java.util.Date;

public class AccessTokenDTO {

    private Integer tokenId;
    private String token;
    private Integer refreshTokenId;
    private Integer clientId;
    private Date createTime;

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

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AccessTokenDTO{");
        sb.append("tokenId=").append(tokenId);
        sb.append(", token='").append(token).append('\'');
        sb.append(", refreshTokenId=").append(refreshTokenId);
        sb.append(", clientId=").append(clientId);
        sb.append(", createTime=").append(createTime);
        sb.append('}');
        return sb.toString();
    }
}
