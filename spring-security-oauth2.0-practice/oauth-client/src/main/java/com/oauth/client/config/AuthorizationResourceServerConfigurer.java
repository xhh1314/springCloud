package com.oauth.client.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

@Configuration
@EnableResourceServer
public class AuthorizationResourceServerConfigurer extends ResourceServerConfigurerAdapter {

    @Autowired
    private DataSource dataSource;


    private String checkTokenEndpointUrl="http://localhost:8709/oauth/check_token";
    public AuthorizationResourceServerConfigurer() {
        super();
    }

    @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);
    }
    @Bean
    public ResourceServerTokenServices resourceServerTokenServices(){
        DefaultTokenServices defaultTokenServices=new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        return defaultTokenServices;
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        super.configure(resources);
        resources.tokenServices(resourceServerTokenServices());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        super.configure(http);
    }
}
