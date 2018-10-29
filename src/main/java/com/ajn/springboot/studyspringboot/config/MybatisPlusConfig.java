package com.ajn.springboot.studyspringboot.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 艾江南
 * @date 2018/9/10
 */
@Configuration
@MapperScan("com.ajn.springboot.studyspringboot.mapper")
public class MybatisPlusConfig {

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

//    @Bean
//    @Profile("test")
//    public PerformanceInterceptor sqlExplainInterceptor() {
//        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
//        performanceInterceptor.setMaxTime(3000);
//        performanceInterceptor.setWriteInLog(false);
//        return performanceInterceptor;
//    }

}
