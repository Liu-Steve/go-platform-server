package com.goplatform.server.service;

import com.goplatform.server.pojo.entity.UserEntity;


public interface UserService {
    public boolean registerUser(UserEntity user);

    public UserEntity getUserInfoById(Long id);
}
