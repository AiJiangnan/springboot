package com.ajn.springboot.studyspringboot.annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RedisCacheable {

    /**
     * 缓存Redis时的Key
     *
     * @return Redis缓存的键
     */
    String value();

    /**
     * 缓存过期时间，单位：毫秒
     *
     * @return 过期时间
     */
    long timeout() default -1;

}
