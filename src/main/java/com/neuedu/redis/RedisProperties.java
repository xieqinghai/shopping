package com.neuedu.redis;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties
@Data
public class RedisProperties {


    // 最大连接数
    @Value("${redis.max.total}")
    private int maxTotal;

    @Value("${redis.max.idle}")
    private int maxIdle;

    @Value("${redis.min.idle}")
    private int minIdle;

    @Value("${redis.ip}")
    private String redisIp;

    @Value("${redis.port}")
    private int redisPort;

    @Value("${redis.test.borrow}")
    private boolean testBorrow;

    @Value("${redis.test.return}")
    private boolean testReturn;

    @Value("${redis.password}")
    private String redisPassword;





}
