package com.goplatform.server.service.impl;

import com.goplatform.server.exception.ExceptionEnum;
import com.goplatform.server.exception.GoServerException;
import com.goplatform.server.pojo.constant.Constants;
import com.goplatform.server.pojo.domain.User;
import com.goplatform.server.pojo.entity.RoleEntity;
import com.goplatform.server.pojo.entity.UserEntity;
import com.goplatform.server.repository.RoleRepository;
import com.goplatform.server.repository.UserRepository;
import com.goplatform.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.goplatform.server.utils.PasswordUtil;
import com.goplatform.server.utils.PublicUtil;
import com.goplatform.server.utils.ValidUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Resource
    UserRepository userRepository;
    @Resource
    RoleRepository roleRepository;
    @Resource
    PasswordEncoder passwordEncoder;    // 在 PasswordConfig 中配置为 BCrypt 算法

    public User registerUser(User user) {
        // 校验用户名是否符合格式
        if (!ValidUtil.isUsername(user.getUsername())) {
            throw new GoServerException(ExceptionEnum.USER_REGISTER_USERNAME_INVALID);
        }
        // 校验密码是否符合格式
        if (!ValidUtil.isPassword(user.getPassword())) {
            throw new GoServerException(ExceptionEnum.USER_REGISTER_PASSWORD_INVALID);
        }
        // 校验邮箱是否符合格式
        if (StringUtils.isNotEmpty(user.getEmail()) && !ValidUtil.isEmail(user.getEmail())) {
            throw new GoServerException(ExceptionEnum.USER_REGISTER_EMAIL_INVALID);
        }
        // 校验用户名是否存在
        UserEntity userFromDB = userRepository.findUserEntityByUsername(user.getUsername());
        if (userFromDB != null) {
            throw new GoServerException(ExceptionEnum.USER_REGISTER_USERNAME_EXIST);
        }
        // 生成UUID以及盐值和加密密码
        UserEntity userEntity = domainToEntity(user);
        long userId = PublicUtil.getUUID();
        userEntity.setId(userId);
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
        // 为用户赋予普通玩家权限
        RoleEntity role = roleRepository.findRoleEntityByName(Constants.ROLE_PLAYER);
        if (role == null) {
            // 此时 player 角色不存在数据库中，可以将 spring.jpa.hibernate.ddl-auto 设为 create 及以上来重建表，注意备份重要数据
            throw new RuntimeException("找不到 player 角色，请检查数据库完整性");
        }
        userEntity.getRoles().add(role);
        // 将用户数据入库
        userRepository.save(userEntity);
        // 将生成的用户信息返回给前端
        return entityToDomain(userEntity);
    }

    @Override
    public User updateUserInfo(long userId, User user) {
        // 校验用户Id
        UserEntity userEntity = userRepository.findById(userId).orElse(null);
        if (userEntity == null) {
            throw new GoServerException(ExceptionEnum.USER_USERID_INVALID);
        }
        // 校验用户名
        return null;
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

    @Override
    public UserEntity getUserInfoByFeature(String feature) {
        UserEntity user;
        if (feature.contains("@")) {
            user = getUserInfoByEmail(feature);
        } else {
            user = getUserInfoByUsername(feature);
        }
        if (user == null) {
            throw new GoServerException(ExceptionEnum.USER_LONGIN_FAILED);
        }
        return user;
    }

    private UserEntity domainToEntity(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(user.getUserId());
        userEntity.setUsername(user.getUsername());
        userEntity.setPassword(user.getPassword());
        userEntity.setEmail(user.getEmail());
        userEntity.setStatus(user.getStatus());
        userEntity.setCreatedDate(user.getCreateDate());
        userEntity.setUpdatedDate(user.getUpdateDate());
        return userEntity;
    }

    private User entityToDomain(UserEntity userEntity) {
        User user = new User();
        user.setUserId(userEntity.getId());
        user.setUsername(userEntity.getUsername());
        user.setEmail(userEntity.getEmail());
        user.setStatus(userEntity.getStatus());
        user.setCreateDate(userEntity.getCreatedDate());
        user.setUpdateDate(userEntity.getUpdatedDate());
        return user;
    }
}
