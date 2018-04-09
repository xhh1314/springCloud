package com.example.myoauth.resources.config;

import com.example.myoauth.constants.OauthHeaderConstants;
import com.example.myoauth.filter.OauthResourceProtectFliter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfig {

    private String checkAccessTokenUrl = "http://localhost:8709/check_access_token";

    @Bean
    public FilterRegistrationBean oauthResourceProtectFliter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new OauthResourceProtectFliter());
        filterRegistrationBean.setEnabled(true);
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter(OauthHeaderConstants.CHECK_ACESS_TOKEN_URL, checkAccessTokenUrl);
        return filterRegistrationBean;

    }


}
