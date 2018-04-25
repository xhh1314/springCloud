package com.example.taobao.order.webservice.config;

import com.example.taobao.common.exception.JedisGetConnectionException;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;


@Configuration
public class JedisConfig {

    @Value("${redis.redisClusterNodes}")
    private String redisClusterNodes;

    @Value("${redis.pool.maxIdle}")
    private int maxIdle;
    @Value("${redis.pool.minIdle}")
    private int minIdle;
    @Value("${redis.pool.maxTotal}")
    private int maxTotal;
    @Value("${redis.pool.maxWaitMillis}")
    private long maxWaitMillis;

    @Bean
    public JedisCluster jedisCluster() {
        if (redisClusterNodes == null)
            throw new JedisGetConnectionException("redis cluster nodes don't be set !  nodes should be specified like  '127.0.0.1:6379,127.0.0.1:6380'");
        Set<HostAndPort> hostAndPortSet = new HashSet<>(8);
        String[] nodes = redisClusterNodes.split(",");
        for (int i = 0; i < nodes.length; i++) {
            String str = nodes[i];
            str = str.trim();
            String[] hostAndPortArray = str.split(":");
            HostAndPort hostAndPort = new HostAndPort(hostAndPortArray[0], Integer.parseInt(hostAndPortArray[1]));
            hostAndPortSet.add(hostAndPort);
        }
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setMaxIdle(maxIdle);
        poolConfig.setMinIdle(minIdle);
        poolConfig.setMaxTotal(maxTotal);
        poolConfig.setMaxWaitMillis(maxWaitMillis);
        JedisCluster cluster = new JedisCluster(hostAndPortSet, 1000, 1000, 5, poolConfig);
        return cluster;
    }


}
