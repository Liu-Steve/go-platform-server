package com.goplatform.server.pojo.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class KataUrl {
    @Value("${katago.url}")
    public String baseUrl;
    public String endCountPath = "/kata/count";
}
