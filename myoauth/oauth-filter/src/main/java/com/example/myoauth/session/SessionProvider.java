package com.example.myoauth.session;

import com.example.myoauth.authentication.Authentication;

public interface SessionProvider {

    Authentication getAuthenticaiton(Authentication authentication);

    Authentication createAuthentication(Authentication authentication);


}
