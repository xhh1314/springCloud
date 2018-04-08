package com.example.myoauth.common.manage;

import com.example.myoauth.common.dao.OauthRefreshTokenMapper;
import com.example.myoauth.common.entity.RefreshTokenDO;
import com.example.myoauth.dto.RefreshTokenDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RefreshTokenManage {

    @Autowired
    private OauthRefreshTokenMapper refreshTokenMapper;

    /**
     * 保存refreshToken
     *
     * @param refreshTokenDTO
     * @return
     */
    public RefreshTokenDTO saveRefreshToken(RefreshTokenDTO refreshTokenDTO) {
        if (refreshTokenDTO == null)
            return null;
        RefreshTokenDO refreshTokenDO = new RefreshTokenDO();
        refreshTokenDO.transferToDo(refreshTokenDTO);
        refreshTokenMapper.saveRefreshToken(refreshTokenDO);
        refreshTokenDTO.setRefreshTokenId(refreshTokenDO.getRefreshTokenId());
        return refreshTokenDTO;

    }

    /**
     * 根据accessTokenId查询refreshToken
     *
     * @param clientKey
     * @return
     */
    public RefreshTokenDTO listRefreshTokenByClientKey(String clientKey) {
        List<RefreshTokenDO> refreshTokenDOList = refreshTokenMapper.listRefreshTokenByClientKey(clientKey);
        if (refreshTokenDOList == null || refreshTokenDOList.isEmpty())
            return null;
        RefreshTokenDO refreshTokenDO = refreshTokenDOList.get(0);
        return refreshTokenDO.transferToDto();
    }

    /**
     * 根据refreshToken id查询
     *
     * @param refreshTokenId
     * @return
     */
    public RefreshTokenDTO getRefreshTokenById(Integer refreshTokenId) {
        RefreshTokenDO refreshTokenDO = refreshTokenMapper.getRefreshTokenById(refreshTokenId);
        if (refreshTokenDO == null)
            return null;
        return refreshTokenDO.transferToDto();
    }
}
