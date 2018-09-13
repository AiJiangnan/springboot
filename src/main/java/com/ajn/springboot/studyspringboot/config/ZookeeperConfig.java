package com.ajn.springboot.studyspringboot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author 艾江南
 * @date 2018/9/13
 */
@Configuration
@ConfigurationProperties(prefix = "spring.zookeeper")
public class ZookeeperConfig {

    private String host = "127.0.0.1:2181";

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}
