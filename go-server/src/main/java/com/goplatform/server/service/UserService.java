package com.goplatform.server.service;

import com.goplatform.server.pojo.domain.User;
import com.goplatform.server.pojo.entity.UserEntity;


public interface UserService {

    public User registerUser(User user);

    public UserEntity getUserInfoById(Long id);

    public UserEntity getUserInfoByUsername(String username);

    public UserEntity getUserInfoByEmail(String email);

}
