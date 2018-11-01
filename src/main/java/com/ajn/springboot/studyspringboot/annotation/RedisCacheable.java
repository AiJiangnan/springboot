package com.ajn.springboot.studyspringboot.annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RedisCacheable {

    String value();

    long timeout() default -1;

}
