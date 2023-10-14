package com.goplatform.server.security;

import com.goplatform.server.pojo.entity.UserEntity;
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
     * @param userId 用户 ID
     * @return JWT Token
     */
    public String generateToken(String userId) {
        Map<String, Object> claims = new HashMap<>();   // 可以自由加入信息
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userId) // 使用用户 ID 作为 Subject
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * 对 Claims 进行验证
     *
     * @param claim JWT claim
     * @param user  用户信息
     * @return 是否合法
     */
    public boolean validateClaim(Claims claim, UserEntity user) {
        //可以验证其他信息，如角色
        return claim.getSubject().equals(String.valueOf(user.getId()))
                && !claim.getExpiration().before(new Date());
    }

}
