package com.goplatform.server.security;

import com.goplatform.server.pojo.entity.RoleEntity;
import com.goplatform.server.pojo.entity.UserEntity;
import com.goplatform.server.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 创建一个UserDetailsService的Bean，从数据库中读取用户和权限信息
 */
@Service
public class DbUserDetailService implements UserDetailsService {

    @Resource
    private UserService userService;

    /**
     * 通过用户名/邮箱查找用户
     *
     * @param userFeature 用户名/邮箱 含 '@' 则认为是邮箱，否则认为是用户名
     * @return 用户信息
     * @throws UsernameNotFoundException 找不到用户
     */
    @Override
    public UserDetails loadUserByUsername(String userFeature) throws UsernameNotFoundException {
        UserEntity user;
        if (userFeature.contains("@")) {
            user = userService.getUserInfoByEmail(userFeature);
        } else {
            user = userService.getUserInfoByUsername(userFeature);
        }
        if (user == null) {
            throw new UsernameNotFoundException("User " + userFeature + " is not found");
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (RoleEntity role : user.getRoles()) {
            for (String auth : role.getAuthorities()) {
                authorities.add(new SimpleGrantedAuthority(auth));
            }
        }
        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(authorities.toArray(new GrantedAuthority[0]))
                .build();
    }

}
