package com.ajn.springboot.studyspringboot.controller;

import com.ajn.springboot.studyspringboot.entity.RestResponse;
import com.ajn.springboot.studyspringboot.model.User;
import com.ajn.springboot.studyspringboot.service.AdminService;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * @author 艾江南
 */
@Validated
@RestController
@RequestMapping("user")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private AdminService adminService;
    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private Job importUserJob;

    @GetMapping
    public RestResponse<User> getUser() {
        return RestResponse.ok(adminService.getOne(null));
    }

    @GetMapping("{pageNum}/{pageSize}")
    public RestResponse<Page<User>> getUserPage(@PathVariable int pageNum, @PathVariable int pageSize, HttpSession session) {
        logger.debug("pageNum:{},pageSize:{}", pageNum, pageSize);
        logger.debug("session:{}", session.getAttribute("abc"));
        Page<User> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        return RestResponse.ok((Page<User>) adminService.page(page, null));
    }

    @PostMapping
    public RestResponse<Boolean> addUser(@Valid User user) {
        logger.debug(user.toString());
        return RestResponse.ok(adminService.save(user));
    }

    @PostMapping("update")
    public RestResponse updateUser() {
        logger.debug("update user");
        User user = new User();
        user.setAmount(new BigDecimal(100));
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", 1);
        adminService.update(user, updateWrapper);
        return RestResponse.ok();
    }

    @PostMapping("date")
    public RestResponse date(User user) {
        logger.debug("时间:{}", user);
        return RestResponse.ok(user);
    }

    @PostMapping("upload")
    public RestResponse upload(MultipartFile file) {
        logger.info("File name:{}", file.getOriginalFilename());
        try {
            file.transferTo(new File("E://" + file.getOriginalFilename()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return RestResponse.ok();
    }

    @GetMapping("batch")
    public RestResponse batch() throws Exception {
        JobParameters jobParameters = new JobParameters();
        jobLauncher.run(importUserJob, jobParameters);
        return RestResponse.ok();
    }

}
