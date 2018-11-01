package com.ajn.springboot.studyspringboot.service;

import com.ajn.springboot.studyspringboot.model.User;
import com.baomidou.mybatisplus.extension.service.IService;

public interface AdminService extends IService<User> {

    User getAnnotation(String name);

}
