package com.ajn.springboot.studyspringboot.service.impl;

import com.ajn.springboot.studyspringboot.mapper.UserMapper;
import com.ajn.springboot.studyspringboot.model.User;
import com.ajn.springboot.studyspringboot.service.AdminService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServieImpl implements AdminService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserMapper userMapper;

    @Override
    public Page<User> selectPage(int num, int size) {
        Page<User> page = new Page<>(num, size);
        QueryWrapper<User> wa = new QueryWrapper<>();
        userMapper.selectPage(page, wa);
        logger.debug("selectPage:{}", page);
        return page;
    }

    @Override
    public User selectUser(Integer id) {
        User user = userMapper.selectById(id);
        logger.debug(user.toString());
        return user;
    }

    @Override
    public User select(String username) {
        return userMapper.selectByUsername(username);
    }

    @Override
    public int addUser(User user) {
        return userMapper.insert(user);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.update(user, null);
    }
}
