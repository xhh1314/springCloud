package com.example.springcloud.bankofchina.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("com.example.springcloud.bankofchina.dao")//注解说明spring-data-jpa的实例路径
//@ImportResource("classpath:/config/TransactionAopConfig.xml")//在xml文件里配置，通过AOP管理事务，直接使用java代码配置的方式不好弄
public class DataSourceConfiguration {
    //这里数据源配置非常重要，如果数据源配置错了，会出现一个莫名奇妙的错误， 而且看不出来是数据源错了,只会提示hibernate的EntityManager初始化错误！
    @Primary
    @Bean
    @ConfigurationProperties("spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

}
