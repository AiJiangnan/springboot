package com.ajn.springboot.studyspringboot.config;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author 艾江南
 */
@Configuration
public class RedisConfig extends CachingConfigurerSupport {

    @Bean
    public StringRedisTemplate redisTemplate(LettuceConnectionFactory lettuceConnectionFactory) {
        StringRedisTemplate redisTemplate = new StringRedisTemplate();
        redisTemplate.setConnectionFactory(lettuceConnectionFactory);
        return redisTemplate;
    }

}
