package com.goplatform.server.controller;

import com.goplatform.server.exception.GoServerException;
import com.goplatform.server.pojo.constant.Constants;
import com.goplatform.server.pojo.domain.Result;
import com.goplatform.server.pojo.entity.UserEntity;
import com.goplatform.server.pojo.entity.UserLoginVO;
import com.goplatform.server.security.DbUserDetailService;
import com.goplatform.server.security.JwtTokenUtil;
import com.goplatform.server.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * 用户接口
 * 负责：用户的登录，注册，修改
 */
@RestController
@RequestMapping("${apiPrefix}")
public class UserController {

    @Resource
    private UserService userService;
    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private JwtTokenUtil jwtTokenUtil;
    @Resource
    private DbUserDetailService userDetailService;

    /**
     * 用户注册接口
     *
     * @param user 前端传入用户信息
     * @return 返回注册结果
     */
    @PostMapping("/register")
    public Result registerUser(@RequestBody UserEntity user) {
        // TODO 具体的注册逻辑
        userService.registerUser(user);
        return Result.ok("注册成功");
    }

    /**
     * 用户登录接口
     *
     * @param user 用户信息
     * @return 登录结果
     */
    @PostMapping("/login")
    public Result login(@RequestBody UserLoginVO user) {
        try {
            final UserDetails userDetails = userDetailService.loadUserByUsername(user.getFeature());
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userDetails.getUsername(), user.getPassword()));
            final String token = jwtTokenUtil.generateToken(userDetails);
            return Result.ok("OK", token);
        } catch (Exception e) {
            return Result.error(Constants.RESULT_LOGIN_FAIL, "登陆失败，用户名、邮箱或密码错误");
        }
    }

    /**
     * 获取用户信息接口
     *
     * @param userId 前端传入用户Id
     * @return 返回用户信息（Json格式）
     */
    @GetMapping("/user/{userId}")
    @PreAuthorize("hasAuthority('user/read')")
    public Result getUserInfo(@PathVariable(value = "userId") Long userId) {
        UserEntity userInfo = userService.getUserInfoById(userId);
        return Result.ok(userInfo);
    }

    /**
     * 更新用户信息
     *
     * @param userId 需要更新的用户Id
     * @param user   更新的内容
     * @return 返回更新的结果
     */
    @PutMapping("/user/{userId}")
    @PreAuthorize("hasAuthority('user/write')")
    public Result updateUserInfo(@PathVariable(value = "userId") Long userId, @RequestBody UserEntity user) {
        // TODO 更新用户信息，并将更新信息返回给前端
        return Result.ok("更新用户信息成功");
    }
}
