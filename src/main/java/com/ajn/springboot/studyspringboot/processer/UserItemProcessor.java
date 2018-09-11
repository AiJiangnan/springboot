package com.ajn.springboot.studyspringboot.processer;

import com.ajn.springboot.studyspringboot.model.User;
import org.springframework.batch.item.ItemProcessor;

import java.util.UUID;

/**
 * @author 艾江南
 * @date 2018/9/7
 */
public class UserItemProcessor implements ItemProcessor<User, User> {

    @Override
    public User process(User user) {
        System.out.println(user);
        String username = user.getUsername().toUpperCase();
        String password = user.getPassword().toUpperCase();
        User newUser = new User();
        newUser.setId(UUID.randomUUID().toString().replace("-", ""));
        newUser.setUsername(username);
        newUser.setPassword(password);
        System.out.println(newUser);
        return newUser;
    }

}
