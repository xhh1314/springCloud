package com.example.taobao.inventory.webservice.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 配置动态数据源
 * 注意：springBoot入口类,需exclude = {DataSourceAutoConfiguration.class}
 */
@Configuration
public class DynamicDatasourceConfig {


    @Bean(name = "datasource0")
    @ConfigurationProperties(prefix = "spring.datasource.db1")
    public DataSource dataSource0() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "datasource1")
    @ConfigurationProperties(prefix = "spring.datasource.db2")
    public DataSource dataSource1() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "datasource")
    public DataSource dynamicDataSource() {
        DynamicDataSource dynamicDatasource = new DynamicDataSource();
        dynamicDatasource.setDefaultTargetDataSource(dataSource0());
        Map<Object, Object> map = new HashMap<>(4);
        map.put("datasource0", dataSource0());
        map.put("datasource1", dataSource1());
        dynamicDatasource.setTargetDataSources(map);
        return dynamicDatasource;

    }

    /**
     * 配置@Transactional注解事物
     *
     * @return
     */
   // @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dynamicDataSource());
    }

}
