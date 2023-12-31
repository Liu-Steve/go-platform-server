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

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Resource
    private UserDetailsService userDetailsService;
    @Resource
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String header = request.getHeader(HttpHeaders.AUTHORIZATION);
            String token = JwtTokenUtil.getTokenFromHeader(header);
            Claims claims = JwtTokenUtil.getClaimFromToken(token);
            long userId = JwtTokenUtil.getUserIdFromClaim(claims);
            // 验证声明
            UserEntity user = userService.getUserInfoById(userId);
            if (user == null) {
                throw new Exception("Fail to find " + userId + " user");
            }
            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
            if (userDetails != null && JwtTokenUtil.validateClaim(claims, user)) {
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
