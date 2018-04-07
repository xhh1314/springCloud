package com.example.myoauth.common.dao;

import com.example.myoauth.common.entity.RefreshTokenDO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OauthRefreshTokenMapper {

	/**
	 * 保存refreshToken
	 * @param refreshTokenDO
	 * @return
	 */
	Integer saveRefreshToken(RefreshTokenDO refreshTokenDO);

	/**
	 * 根据accessTokenId查询refreshToken
	 * @param tokenId
	 * @return
	 */
	List<RefreshTokenDO> listRefreshTokenByAccessTokenId(Integer tokenId);

	/**
	 * 根据refreshToken id查询
	 * @param refreshTokenId
	 * @return
	 */
	RefreshTokenDO getRefreshTokenById(Integer refreshTokenId);
}
