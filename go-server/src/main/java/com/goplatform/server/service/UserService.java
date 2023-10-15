package com.goplatform.server.service;

import com.goplatform.server.pojo.domain.User;
import com.goplatform.server.pojo.entity.UserEntity;


public interface UserService {

    User registerUser(User user);

    User updateUserInfo(long userId, User user);

    UserEntity getUserInfoById(Long id);

    UserEntity getUserInfoByUsername(String username);

    UserEntity getUserInfoByEmail(String email);

    UserEntity getUserInfoByFeature(String feature);



}
