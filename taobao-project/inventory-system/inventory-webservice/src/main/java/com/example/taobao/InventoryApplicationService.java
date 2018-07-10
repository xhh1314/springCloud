package com.example.taobao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@MapperScan(basePackages = "com.example.taobao.inventory.common.dao")
public class InventoryApplicationService {

    public static void main(String[] args) {
        SpringApplication.run(InventoryApplicationService.class, args);
    }
}
