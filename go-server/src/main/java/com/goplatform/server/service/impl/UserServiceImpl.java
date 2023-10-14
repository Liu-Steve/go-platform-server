package com.goplatform.server.service.impl;

import com.goplatform.server.exception.ExceptionEnum;
import com.goplatform.server.exception.GoServerException;
import com.goplatform.server.pojo.domain.User;
import com.goplatform.server.pojo.entity.UserEntity;
import com.goplatform.server.repository.UserRepository;
import com.goplatform.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

@Service
@Transactional
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
    public User registerUser(User user) {
        // 校验用户名是否符合格式
        if (!ValidUtil.isUsername(user.getUsername())) {
            throw new GoServerException(ExceptionEnum.USER_USERNAME_INVALID);
        }
        // 校验密码是否符合格式
        if (!ValidUtil.isPassword(user.getPassword())) {
            throw new GoServerException(ExceptionEnum.USER_PASSWORD_INVALID);
        }
        // 校验邮箱是否符合格式
        if (StringUtils.isNotEmpty(user.getEmail()) && !ValidUtil.isEmail(user.getEmail())) {
            throw new GoServerException(ExceptionEnum.USER_EMAIL_INVALID);
        }
        // 校验用户名是否存在
        UserEntity userFromDB = userRepository.findByUsername(user.getUsername());
        if (userFromDB != null) {
            throw new GoServerException(ExceptionEnum.USER_USERNAME_EXIST);
        }
        // 生成UUID以及盐值和加密密码
        UserEntity userEntity = domainToEntity(user);
        long userId = PublicUtil.getUUID();
        userEntity.setId(userId);
        String salt = PasswordUtil.getSalt();
        String encryptPassword = PasswordUtil.encrypt(user.getPassword(), salt);
        userEntity.setSalt(salt);
        userEntity.setPassword(encryptPassword);
        // 将用户数据入库
        userRepository.save(userEntity);
        // 将生成的用户信息返回给前端
        return entityToDomain(userEntity);
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
