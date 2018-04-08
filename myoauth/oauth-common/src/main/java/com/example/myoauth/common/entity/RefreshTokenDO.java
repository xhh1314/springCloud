package com.example.myoauth.common.entity;

import com.example.myoauth.dto.RefreshTokenDTO;
import org.springframework.beans.BeanUtils;

import java.util.Date;

public class RefreshTokenDO implements Cloneable {

    private Integer refreshTokenId;
    private String refreshToken;
    private Date createTime;
    private String clientKey;
    private Integer expiresTime;
    public Integer getRefreshTokenId() {
        return refreshTokenId;
    }

    public void setRefreshTokenId(Integer refreshTokenId) {
        this.refreshTokenId = refreshTokenId;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getClientKey() {
        return clientKey;
    }

    public void setClientKey(String clientKey) {
        this.clientKey = clientKey;
    }

    public Integer getExpiresTime() {
        return expiresTime;
    }

    public void setExpiresTime(Integer expiresTime) {
        this.expiresTime = expiresTime;
    }

    public RefreshTokenDTO transferToDto() {
        RefreshTokenDTO refreshTokenDTO = new RefreshTokenDTO();
        BeanUtils.copyProperties(this, refreshTokenDTO);
        return refreshTokenDTO;


    }

    public RefreshTokenDO transferToDo(RefreshTokenDTO refreshTokenDTO) {
        BeanUtils.copyProperties(refreshTokenDTO, this);
        return this;

    }
}
