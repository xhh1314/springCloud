package com.example.myoauth.common.dao;

import com.example.myoauth.common.entity.AccessTokenDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OauthAccessTokenMapper {

	Integer saveAccessToken(AccessTokenDO accessTokenDO);

	 List<AccessTokenDO> listAccessTokenByClientKey(String clientKey);

	void deleteAccessTokenBy(String clientKey);

	void updateAccessTokenCreateTime(@Param("accessToken") String accessToken, @Param("currentTime") Date currentTime);

}
