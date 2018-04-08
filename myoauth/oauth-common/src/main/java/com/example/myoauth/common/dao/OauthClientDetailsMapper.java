package com.example.myoauth.common.dao;

import com.example.myoauth.common.entity.ClientDetailsDO;
import org.springframework.stereotype.Repository;

@Repository
public interface OauthClientDetailsMapper {

    ClientDetailsDO getClientDetailsByClientKey(String clientKey);

}
