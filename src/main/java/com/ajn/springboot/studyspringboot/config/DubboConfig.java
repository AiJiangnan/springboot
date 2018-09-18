package com.ajn.springboot.studyspringboot.config;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 艾江南
 * @date 2018/9/18
 */
@Configuration
@DubboComponentScan(basePackages = "com.ajn.springboot.studyspringboot.service.impl")
public class DubboConfig {

    @Bean
    @ConfigurationProperties("spring.dubbo.application")
    public ApplicationConfig applicationConfig() {
        return new ApplicationConfig();
    }

    @Bean
    @ConfigurationProperties("spring.dubbo.protocol")
    public ProtocolConfig protocolConfig() {
        return new ProtocolConfig();
    }

    @Bean
    @ConfigurationProperties("spring.dubbo.registry")
    public RegistryConfig registryConfig() {
        return new RegistryConfig();
    }
}
