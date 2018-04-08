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
	 * @param clientKey
	 * @return
	 */	List<RefreshTokenDO> listRefreshTokenByClientKey(String  clientKey);


	/**
	 * 根据refreshToken id查询
	 * @param refreshToken
	 * @return
	 */
	RefreshTokenDO getRefreshTokenById(String refreshToken);
}
