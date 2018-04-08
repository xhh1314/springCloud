package com.example.myoauth.dto;

import java.util.Date;

public class RefreshTokenDTO implements Cloneable {

	private String refreshToken;
	private Date createTime;
	private String clientKey;
	private Integer expiresTime;

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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RefreshTokenDTO{");
        sb.append("refreshToken='").append(refreshToken).append('\'');
        sb.append(", createTime=").append(createTime);
        sb.append(", clientKey='").append(clientKey).append('\'');
        sb.append(", expiresTime=").append(expiresTime);
        sb.append('}');
        return sb.toString();
    }
}
