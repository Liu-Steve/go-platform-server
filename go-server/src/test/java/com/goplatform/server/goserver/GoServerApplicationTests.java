package com.goplatform.server.goserver;

import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class GoServerApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private StringEncryptor encryptor;

    @Test
    public void getPass() {
        String name = encryptor.encrypt("name");
        String password = encryptor.encrypt("password");
        String jwt = encryptor.encrypt("openssl rand -hex 64");
        System.out.println("database name: " + name);
        System.out.println("database password: " + password);
        System.out.println("jwt secret: " + jwt);
        Assert.isTrue(name.length() > 0, "name is empty");
        Assert.isTrue(password.length() > 0, "password is empty");
        Assert.isTrue(jwt.length() > 0, "jwt secret is empty");
    }

}
