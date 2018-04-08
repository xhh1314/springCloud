package com.example.myoauth.common.manage;

import com.example.myoauth.common.dao.OauthClientDetailsMapper;
import com.example.myoauth.common.entity.ClientDetailsDO;
import com.example.myoauth.dto.ClientDetailsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClientDetailsManage {

    @Autowired
    private OauthClientDetailsMapper clientDetailsMapper;

    public ClientDetailsDTO getClientDetailsByClientKey(String clientKey) {
        ClientDetailsDO clientDetailsDO = clientDetailsMapper.getClientDetailsByClientKey(clientKey);
        if (clientDetailsDO == null)
            return null;
        return clientDetailsDO.transfertoDTO();

    }

}
