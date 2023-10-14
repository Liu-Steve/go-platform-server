package com.goplatform.server.security;

import com.goplatform.server.pojo.entity.UserEntity;
import com.goplatform.server.service.UserService;
import io.jsonwebtoken.Claims;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService;
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;

    public JwtRequestFilter(UserDetailsService userDetailsService, JwtTokenUtil jwtTokenUtil, UserService userService) {
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        // 未找到 JWT Token
        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        try {
            final String token = header.substring(7);   // 去除 Bearer 开头
            Claims claims = jwtTokenUtil.getClaimFromToken(token);  // 解析 Token，不合法会抛出异常
            // 验证声明
            UserEntity user = userService.getUserInfoById(Long.parseLong(claims.getSubject()));
            if (user == null) {
                throw new Exception("Fail to find " + Long.parseLong(claims.getSubject()) + " user");
            }
            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
            if (userDetails != null && jwtTokenUtil.validateClaim(claims, user)) {
                // 创建一个身份令牌放入context中，后面的过滤器可以使用
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(
                                user, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        } catch (Exception e) {
            logger.warn(e);
        }
        filterChain.doFilter(request, response);
    }

}
