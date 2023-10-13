package com.goplatform.server.utils;

import java.util.regex.Pattern;

/**
 * 参数校验类
 */
public class ValidUtil {
    /**
     * 用户名只能包含字母、数字、下划线、横杠和汉字
     * 用户名必须以字母或汉字开头
     * 用户名长度必须在1到15个字符之间
     */
    public static final String REGEX_USERNAME = "^[\\u4e00-\\u9fa5A-Za-z][\\u4e00-\\u9fa5A-Za-z0-9_-]{0,14}$";

    /**
     * 密码只能包含字母、数字和特殊字符
     * 密码长度必须在6到20个字符之间
     */
    public static final String REGEX_PASSWORD = "^[a-zA-Z0-9!@#$%^&*()_+\\-=\\[\\]{};':\\\"\\|,.<>\\/?]{6,20}$";

    /**
     * 邮箱只能包含字母、数字、下划线、横杠和点
     * 邮箱必须以字母或数字开头
     * 邮箱长度必须在5到30个字符之间
     * 邮箱中的域名必须包含一个点，且点不能在开头或结尾
     */
    public static final String REGEX_EMAIL = "^[A-Za-z0-9][A-Za-z0-9_.-]{3,28}[A-Za-z0-9]@[A-Za-z0-9]+([.][A-Za-z0-9]+)+$";

    /**
     * 校验用户名
     *
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isUsername(String username) {
        return Pattern.matches(REGEX_USERNAME, username);
    }

    /**
     * 校验密码
     *
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isPassword(String password) {
        return Pattern.matches(REGEX_PASSWORD, password);
    }

    /**
     * 校验邮箱
     *
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isEmail(String email) {
        return Pattern.matches(REGEX_EMAIL, email);
    }

}
