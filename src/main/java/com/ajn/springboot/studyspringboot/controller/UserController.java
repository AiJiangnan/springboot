package com.ajn.springboot.studyspringboot.controller;

import com.ajn.springboot.studyspringboot.entity.RestResponse;
import com.ajn.springboot.studyspringboot.model.User;
import com.ajn.springboot.studyspringboot.service.AdminService;
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
import javax.validation.constraints.NotBlank;
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
    public RestResponse<User> getUser(@NotBlank(message = "登录名不能为空") String word, HttpSession session) {
        logger.debug(word);
        session.setAttribute("abc", "123");
        return RestResponse.ok(adminService.selectUser(1));
    }

    @GetMapping("{pageNum}/{pageSize}")
    public RestResponse<Page<User>> getUserPage(@PathVariable int pageNum, @PathVariable int pageSize, HttpSession session) {
        logger.debug("pageNum:{},pageSize:{}", pageNum, pageSize);
        logger.debug("session:{}", session.getAttribute("abc"));
        return RestResponse.ok(adminService.selectPage(pageNum, pageSize));
    }

    @PostMapping
    public RestResponse<Integer> addUser(@Valid User user) {
        logger.debug(user.toString());
        return RestResponse.ok(adminService.addUser(user));
    }

    @PostMapping("update")
    public RestResponse updateUser() {
        logger.debug("update user");
        User user = new User();
        user.setAmount(new BigDecimal(100));
        adminService.updateUser(user);
        return RestResponse.ok();
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
