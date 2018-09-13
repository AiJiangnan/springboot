package com.ajn.springboot.studyspringboot.controller;

import com.ajn.springboot.studyspringboot.entity.RestResponse;
import com.ajn.springboot.studyspringboot.service.ZookeeperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 艾江南
 * @date 2018/9/13
 */
@RestController
@RequestMapping("zookeeper")
public class ZookeeperController {

    @Autowired
    private ZookeeperService zookeeperService;

    @GetMapping
    public RestResponse<String> get() {
        return RestResponse.ok(zookeeperService.get());
    }

    @GetMapping("watch")
    public RestResponse watch() {
        zookeeperService.watch();
        return RestResponse.ok();
    }

}
