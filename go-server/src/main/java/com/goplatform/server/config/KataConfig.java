package com.goplatform.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class KataConfig {
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
