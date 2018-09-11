package com.ajn.springboot.studyspringboot.service;

import com.ajn.springboot.studyspringboot.model.User;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

public interface AdminService {

    Page<User> selectPage(int num, int size);

    User selectUser(Integer id);

    int addUser(User user);

    int updateUser(User user);

    User select(String username);

}
