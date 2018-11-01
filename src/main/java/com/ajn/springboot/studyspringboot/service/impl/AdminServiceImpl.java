package com.ajn.springboot.studyspringboot.service.impl;

import com.ajn.springboot.studyspringboot.annotation.RedisCacheable;
import com.ajn.springboot.studyspringboot.mapper.UserMapper;
import com.ajn.springboot.studyspringboot.model.User;
import com.ajn.springboot.studyspringboot.service.AdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 艾江南
 */
@Service
@com.alibaba.dubbo.config.annotation.Service
public class AdminServiceImpl extends ServiceImpl<UserMapper, User> implements AdminService {

    final private Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

    @RedisCacheable(value = "redis-key", timeout = 30000)
    @Override
    public User getAnnotation(String name) {
        logger.info("Args: name:{}", name);
        User user = new User();
        user.setId(name);
        user.setUsername("123");
        user.setPassword("456");
        user.setCreateTime(new Date());
        user.setAmount(new BigDecimal("0"));
        user.setTotal("123123");
        return user;
    }
}
