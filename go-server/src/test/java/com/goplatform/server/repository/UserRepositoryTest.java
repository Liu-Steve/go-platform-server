package com.goplatform.server.repository;

import com.goplatform.server.pojo.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Resource
    UserRepository userRepository;
    @Test
    void updateStatusByUserId() {
        int res = userRepository.updateStatusByUserId(UserEntity.USER_STATUS_BUSY, 1697373778885L);
        System.out.println(res);
    }
}