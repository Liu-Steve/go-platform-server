package com.goplatform.server.controller;

import com.goplatform.server.pojo.entity.UserEntity;
import com.goplatform.server.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户接口
 * 负责：用户的登录，注册，修改
 */
@RestController
public class UserController {
    @Resource
    private UserService userService;

    /**
     * 用户注册接口
     * @param user 前端传入用户信息
     * @return 返回注册结果
     */
    @PostMapping("/user/register")
    public String registerUser(@RequestBody UserEntity user) {
        // TODO 具体的注册逻辑
        return null;
    }

    /**
     * 用户登录接口
     * @param user 用户信息
     * @return 登录结果
     */
    @PostMapping("/user/login")
    public String login(@RequestBody UserEntity user) {
        // TODO 具体的登录逻辑
        return null;
    }

    /**
     * 获取用户信息接口
     * @param userId 前端传入用户Id
     * @return 返回用户信息（Json格式）
     */
    @GetMapping("/user/{userId}")
    public String getUserInfo(@PathVariable(value = "userId") Long userId) {
        return userService.getUserInfoById(userId).toString();
    }

    /**
     * 更新用户信息
     * @param userId 需要更新的用户Id
     * @param user 更新的内容
     * @return 返回更新的结果
     */
    @PutMapping("/user/{userId}")
    public String updateUserInfo(@PathVariable(value = "userId") Long userId, @RequestBody UserEntity user) {
        // TODO 更新用户信息，并将更新信息返回给前端
        return null;
    }
}
