package com.ajn.springboot.studyspringboot.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.SqlExplainInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Properties;

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

    @Bean
    @Profile("test")
    public SqlExplainInterceptor sqlExplainInterceptor() {
        SqlExplainInterceptor sqlExplainInterceptor = new SqlExplainInterceptor();
        Properties properties = new Properties();
        properties.setProperty("stopProceed", "true");
        sqlExplainInterceptor.setProperties(properties);
        return sqlExplainInterceptor;
    }

}
