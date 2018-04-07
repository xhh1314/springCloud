package com.example.myoauth.common.entity;

import com.example.myoauth.dto.AccessTokenDTO;
import org.springframework.beans.BeanUtils;

import java.util.Date;

public class AccessTokenDO {

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

	public AccessTokenDTO tranferToDTO() {
		Object o = null;
		try {
			o = this.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		if (o != null) {
			return (AccessTokenDTO) o;
		} else {
			AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
			BeanUtils.copyProperties(this, accessTokenDTO);
			return accessTokenDTO;
		}

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
		sb.append(", clientId=").append(clientId);
		sb.append(", createTime=").append(createTime);
		sb.append('}');
		return sb.toString();
	}
}
