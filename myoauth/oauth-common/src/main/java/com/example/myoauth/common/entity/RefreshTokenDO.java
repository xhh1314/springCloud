package com.example.myoauth.common.entity;

import com.example.myoauth.dto.RefreshTokenDTO;
import org.springframework.beans.BeanUtils;

import java.util.Date;

public class RefreshTokenDO {

	private Integer refreshTokenId;
	private String refreshToken;
	private Integer accessTokenId;
	private Date createTime;

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

	public Integer getAccessTokenId() {
		return accessTokenId;
	}

	public void setAccessTokenId(Integer accessTokenId) {
		this.accessTokenId = accessTokenId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("RefreshTokenDO{");
		sb.append("refreshTokenId=").append(refreshTokenId);
		sb.append(", refreshToken='").append(refreshToken).append('\'');
		sb.append(", accessTokenId=").append(accessTokenId);
		sb.append(", createTime=").append(createTime);
		sb.append('}');
		return sb.toString();
	}

	public RefreshTokenDTO transferToDto() {
		Object o = null;
		try {
			o = this.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		if (o != null) {
			return (RefreshTokenDTO) o;
		} else {
			RefreshTokenDTO refreshTokenDTO = new RefreshTokenDTO();
			BeanUtils.copyProperties(this, refreshTokenDTO);
			return refreshTokenDTO;
		}

	}

	public RefreshTokenDO transferToDo(RefreshTokenDTO refreshTokenDTO) {
		BeanUtils.copyProperties(refreshTokenDTO, this);
		return this;

	}
}
