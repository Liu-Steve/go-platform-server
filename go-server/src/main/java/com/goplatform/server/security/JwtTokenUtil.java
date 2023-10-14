package com.goplatform.server.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 解析和验证JWT令牌工具类
 */
@Component
public class JwtTokenUtil {

    /**
     * token 过期时间
     */
    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60 * 1000;

    /**
     * token 的密钥
     */
    @Value("${jwt.secret}")
    private String secret;

    /**
     * 从 Token 中获得 Claims，不合法会抛出异常
     *
     * @param token JWT Token 字符串
     * @return JWT Claims 内容
     */
    public Claims getClaimFromToken(String token) throws ExpiredJwtException, UnsupportedJwtException,
            MalformedJwtException, SignatureException, IllegalArgumentException {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 生成 Token
     *
     * @param userDetails 用户信息
     * @return JWT Token
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();   // 可以自由加入信息
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * 对 Claims 进行验证
     *
     * @param claim       JWT claim
     * @param userDetails 用户信息
     * @return 是否合法
     */
    public boolean validateClaim(Claims claim, UserDetails userDetails) {
        //可以验证其他信息，如角色
        return userDetails != null &&
                claim.getSubject().equals(userDetails.getUsername())
                && !claim.getExpiration().before(new Date());
    }

}
