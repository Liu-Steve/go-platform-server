package com.goplatform.server.pojo.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class UserLoginVO {

    String feature;

    String password;

}
