package com.example.taobao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.example.taobao.inventory.common.dao")
public class InventoryApplicationService {

    public static void main(String[] args) {
        SpringApplication.run(InventoryApplicationService.class, args);
    }
}
