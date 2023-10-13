package com.goplatform.server.pojo.domain;

import lombok.Data;

import java.util.Date;

@Data
public class User {

    private static final int STATUE_FREE = 0;

    private static final int STATUE_CREATE = 1;

    private static final int STATUE_PARTNER = 2;

    private long userId;

    private String username;

    private String password;

    private String email;

    private int status;

    private Date createDate;

    private Date updateDate;
}
