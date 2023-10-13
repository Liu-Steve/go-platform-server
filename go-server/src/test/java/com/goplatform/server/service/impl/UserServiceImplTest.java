package com.goplatform.server.service.impl;

import com.goplatform.server.pojo.domain.User;
import com.goplatform.server.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class UserServiceImplTest {

    @Resource
    UserService userService;
    @Test
    public void registerUser() {
        User user = new User();
        user.setUsername("DragonDJ");
        user.setPassword("password");
        userService.registerUser(user);
    }
}