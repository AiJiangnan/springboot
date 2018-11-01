package com.ajn.springboot.studyspringboot.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisUtils {

    final static public Integer PERSIST_TIME = -1;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public String getString(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    public void setString(String key, String value) {
        setString(key, value, PERSIST_TIME);
    }

    public void setString(String key, String value, long timeout) {
        stringRedisTemplate.opsForValue().set(key, value, timeout, TimeUnit.MILLISECONDS);
    }

}
