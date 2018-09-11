package com.ajn.springboot.studyspringboot.mapper;

import com.ajn.springboot.studyspringboot.model.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author 艾江南
 * @date 2018/8/29
 */
public interface UserMapper extends BaseMapper<User> {

    User selectByUsername(String username);

}
