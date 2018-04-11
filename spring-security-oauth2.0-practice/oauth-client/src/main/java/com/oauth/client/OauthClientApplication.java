package com.oauth.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class OauthClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(OauthClientApplication.class,args);
    }
}
