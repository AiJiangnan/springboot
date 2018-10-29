package com.ajn.springboot.studyspringboot.service;

import com.ajn.springboot.studyspringboot.BaseTest;
import com.ajn.springboot.studyspringboot.model.User;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class AdminServiceTest extends BaseTest {

    final private Logger logger = LoggerFactory.getLogger(AdminServiceTest.class);

    @Autowired
    private AdminService adminService;

    @Test
    public void insertBatch() {
        Collection<User> list = new ArrayList<>();
        User user = new User();
        user.setId("2");
        user.setUsername("123");
        user.setPassword("123");
        user.setCreateTime(new Date());
        user.setAmount(new BigDecimal("0"));
        user.setTotal("123");
        list.add(user);
        user = new User();
        user.setId("3");
        user.setUsername("456");
        user.setPassword("456");
        user.setCreateTime(new Date());
        user.setAmount(new BigDecimal("0"));
        user.setTotal("456");
        list.add(user);
        adminService.saveBatch(list);
        logger.info("Execute finished!");
    }

}
