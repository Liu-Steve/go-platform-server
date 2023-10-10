package com.goplatform.server.service.impl;

import com.goplatform.server.pojo.entity.UserEntity;
import com.goplatform.server.repository.UserRepository;
import com.goplatform.server.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserRepository userRepository;
    @Override
    public boolean registerUser(UserEntity user) {
        userRepository.save(user);
        return true;
    }

    @Override
    public UserEntity getUserInfoById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
