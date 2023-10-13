package com.goplatform.server.utils;

import org.springframework.util.DigestUtils;

import java.util.Random;

/**
 * 密码工具类
 */
public class PasswordUtil {

    private static final int SALT_COUNT = 10;
    /**
     * 生成salt的静态方法
     * @return 返回盐值
     */
    public static String getSalt(){
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()".toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < SALT_COUNT; i ++ ){
            char aChar = chars[new Random().nextInt(chars.length)];
            sb.append(aChar);
        }
        return sb.toString();
    }

    public static String encrypt(String password, String salt) {
        String saltPassword = DigestUtils.md5DigestAsHex((salt + password).getBytes());
        return salt + "$" + saltPassword;
    }
}
