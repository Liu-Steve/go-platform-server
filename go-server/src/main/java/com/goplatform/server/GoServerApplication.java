package com.goplatform.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class GoServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(GoServerApplication.class, args);
    }

}
