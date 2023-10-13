package com.goplatform.server.utils;


import org.junit.jupiter.api.Test;

public class ValidUtilTest {

    @Test
    public void isUsername() {
        String username = "年";
        System.out.println(ValidUtil.isUsername(username));
    }

    @Test
    public void isPassword() {
        String pwd = "-ad1.$";
        System.out.println(ValidUtil.isPassword(pwd));
    }

    @Test
    public void isEmail() {
    }
}