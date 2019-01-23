package com.neuedu.redis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Java配置类,理解成容器
 * springioc的第三种实现方式
 * */
@Component
@Configuration
public class RedisPool {

    @Autowired
    RedisProperties redisProperties;

    @Bean
    public JedisPool jedisPool(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(redisProperties.getMaxTotal());
        jedisPoolConfig.setMaxIdle(redisProperties.getMaxIdle());
        jedisPoolConfig.setMinIdle(redisProperties.getMinIdle());
        jedisPoolConfig.setTestOnBorrow(redisProperties.isTestBorrow());
        jedisPoolConfig.setTestOnReturn(redisProperties.isTestReturn());

        //阻塞, 当连接池中连接消耗完毕,true:等待连接, false:抛出异常
        jedisPoolConfig.setBlockWhenExhausted(true);

        return new JedisPool(jedisPoolConfig, redisProperties.getRedisIp(),
                redisProperties.getRedisPort(), 2000,
                redisProperties.getRedisPassword(), 0);
    }


}
