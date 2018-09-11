package com.ajn.springboot.studyspringboot.controller;

import com.ajn.springboot.studyspringboot.entity.RestResponse;
import com.ajn.springboot.studyspringboot.model.User;
import com.ajn.springboot.studyspringboot.service.AdminService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * @author 艾江南
 */
@Api(tags = "用户相关")
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

    @ApiOperation("获取用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "word", value = "测试", paramType = "query"),
            @ApiImplicitParam(name = "password", value = "密码", paramType = "query")
    })
    @GetMapping
    public RestResponse<User> getUser(@NotBlank(message = "登录名不能为空") String word) {
        logger.debug(word);
        return RestResponse.ok(adminService.selectUser(1));
    }

    @ApiOperation("分页查询用户")
    @GetMapping("{pageNum}/{pageSize}")
    public RestResponse<Page<User>> getUserPage(@PathVariable int pageNum, @PathVariable int pageSize) {
        logger.debug("pageNum:{},pageSize:{}", pageNum, pageSize);
        return RestResponse.ok(adminService.selectPage(pageNum, pageSize));
    }

    @ApiOperation("新增用户")
    @PostMapping
    public RestResponse<Integer> addUser(@Valid User user) {
        logger.debug(user.toString());
        return RestResponse.ok(adminService.addUser(user));
    }

    @ApiOperation("修改用户")
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
