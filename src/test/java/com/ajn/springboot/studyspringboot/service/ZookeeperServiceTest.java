package com.ajn.springboot.studyspringboot.service;

import com.ajn.springboot.studyspringboot.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ZookeeperServiceTest extends BaseTest {

    @Autowired
    private ZookeeperService zookeeperService;

    @Test
    public void getZookeeper() {
        zookeeperService.get();
    }
}