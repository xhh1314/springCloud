package com.example.myoauth.common.manage;

import com.example.myoauth.common.dao.OauthAccessTokenMapper;
import com.example.myoauth.common.entity.AccessTokenDO;
import com.example.myoauth.dto.AccessTokenDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AccessTokenManage {

	@Autowired
	private OauthAccessTokenMapper accessTokenMapper;

	public AccessTokenDTO saveAccessToken(AccessTokenDTO accessTokenDTO) {
		if (accessTokenDTO == null)
			return null;
		AccessTokenDO accessTokenDO = new AccessTokenDO();
		accessTokenDO.tranferToDO(accessTokenDTO);
		Integer tokenId = accessTokenMapper.saveAccessToken(accessTokenDO);
		accessTokenDTO.setTokenId(accessTokenDO.getTokenId());
		return accessTokenDTO;
	}

	public AccessTokenDTO getAccessTokenByClientKey(String clientKey) {
		List<AccessTokenDO> accessTokenDOList = accessTokenMapper.listAccessTokenByClientKey(clientKey);
		if (accessTokenDOList == null || accessTokenDOList.isEmpty())
			return null;
		AccessTokenDO accessTokenDO = accessTokenDOList.get(0);
		AccessTokenDTO accessTokenDTO = accessTokenDO.tranferToDTO();
		return accessTokenDTO;
	}

	public void deleteAccessTokenBy(String clientKey) {
	}
}
