package com.ajn.springboot.studyspringboot.aop;

import com.ajn.springboot.studyspringboot.annotation.RedisCacheable;
import com.ajn.springboot.studyspringboot.util.RedisUtils;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AnnotationAspect {

    final private Logger logger = LoggerFactory.getLogger(AnnotationAspect.class);

    @Autowired
    private RedisUtils redisUtils;

    @Pointcut("execution(* com.ajn.springboot.studyspringboot.service..*.*(..))")
    private void pointcut() {
    }

    @Around("pointcut() && @annotation(cache)")
    public Object doAround(ProceedingJoinPoint joinPoint, RedisCacheable cache) throws Throwable {
        String key = cache.value();
        long timeout = cache.timeout();
        if (StringUtils.isBlank(key)) {
            throw new NullPointerException("the redis cache key is blank");
        }
        String value = redisUtils.getString(key);
        logger.debug("redis get value: {}", value);
        if (value != null) {
            Class<?> returnType = ((MethodSignature) joinPoint.getSignature()).getReturnType();
            return JSON.parseObject(value, returnType);
        }
        Object proceed = joinPoint.proceed();
        String jsonString = JSON.toJSONString(proceed);
        redisUtils.setString(key, jsonString, timeout);
        logger.debug("redis set key:{},value:{},timeout:{}", key, jsonString, timeout);
        return proceed;
    }

}
