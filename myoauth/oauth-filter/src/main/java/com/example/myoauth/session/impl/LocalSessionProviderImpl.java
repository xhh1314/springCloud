package com.example.myoauth.session.impl;

import com.example.myoauth.authentication.Authentication;
import com.example.myoauth.session.SessionProvider;

import java.util.concurrent.ConcurrentHashMap;

public class LocalSessionProviderImpl implements SessionProvider {

    private final ConcurrentHashMap<Authentication, Authentication> session = new ConcurrentHashMap(1024);

    @Override
    public Authentication getAuthenticaiton(Authentication authentication) {
        Authentication currentAuthentication = session.get(authentication);
        if (currentAuthentication == null)
            return null;
        currentAuthentication.setCreateTime(System.currentTimeMillis() / 1000);
        session.put(currentAuthentication, currentAuthentication);
        return currentAuthentication;
    }

    @Override
    public Authentication createAuthentication(Authentication authentication) {
        return session.put(authentication, authentication);
    }
}
