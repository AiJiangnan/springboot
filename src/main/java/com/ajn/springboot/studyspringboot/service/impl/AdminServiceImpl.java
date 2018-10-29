package com.ajn.springboot.studyspringboot.service.impl;

import com.ajn.springboot.studyspringboot.mapper.UserMapper;
import com.ajn.springboot.studyspringboot.model.User;
import com.ajn.springboot.studyspringboot.service.AdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author 艾江南
 */
@Service
@com.alibaba.dubbo.config.annotation.Service
public class AdminServiceImpl extends ServiceImpl<UserMapper, User> implements AdminService {

}
