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
    private static String secret;

    @Value("${jwt.secret}")
    public void setSecret(String s) {
        secret = s;
    }

    /**
     * 从 JWT Claims 中获取用户 ID
     *
     * @param claims JWT Claims
     * @return 用户 ID
     * @throws NumberFormatException 获取 ID 失败
     */
    public static long getUserIdFromClaim(Claims claims) throws NumberFormatException {
        return Long.parseLong(claims.getSubject());
    }

    /**
     * 从 Token 中获得 Claims，不合法会抛出异常
     *
     * @param token JWT Token 字符串
     * @return JWT Claims 内容
     */
    public static Claims getClaimFromToken(String token) throws ExpiredJwtException, UnsupportedJwtException,
            MalformedJwtException, SignatureException, IllegalArgumentException {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 从请求头 Authorization 获取 JWT Token
     *
     * @param header 请求头中 Authorization 对应的字符串
     * @return JWT Token
     */
    public static String getTokenFromHeader(String header) {
        if (header == null || !header.startsWith("Bearer ")) {
            return null;
        }
        return header.substring(7);
    }

    /**
     * 生成 Token
     *
     * @param userId 用户 ID
     * @return JWT Token
     */
    public static String generateToken(String userId) {
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
    public static boolean validateClaim(Claims claim, UserEntity user) {
        //可以验证其他信息，如角色
        return claim.getSubject().equals(String.valueOf(user.getId()))
                && !claim.getExpiration().before(new Date());
    }

}
