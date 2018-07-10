package com.example.test.proxytest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ProxyTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProxyTestApplication.class, args);
    }

    @Value("${server.port}")
    private String port;

    @RequestMapping(value = "/proxy-test/getPort", method = RequestMethod.GET)
    public String getServerName() {
        return "当前端口号:" + port;
    }
}
