package com.goplatform.server.pojo.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
public class UserLoginVO {

    String feature;

    String password;

}
