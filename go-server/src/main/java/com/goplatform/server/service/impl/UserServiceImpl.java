package com.goplatform.server.service.impl;

import com.goplatform.server.pojo.entity.UserEntity;
import com.goplatform.server.repository.UserRepository;
import com.goplatform.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserRepository userRepository;
    @Resource
    PasswordEncoder passwordEncoder;    // 在 GoServerApplication 中配置为 BCrypt 算法

    @Override
    public boolean registerUser(UserEntity user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));   // 防拖库
        userRepository.save(user);
        return true;
    }

    @Override
    public UserEntity getUserInfoById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public UserEntity getUserInfoByUsername(String username) {
        return userRepository.findUserEntityByUsername(username);
    }

    @Override
    public UserEntity getUserInfoByEmail(String email) {
        return userRepository.findUserEntityByEmail(email);
    }
}
