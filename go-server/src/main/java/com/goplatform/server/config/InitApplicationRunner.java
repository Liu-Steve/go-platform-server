package com.goplatform.server.config;

import com.goplatform.server.repository.UserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 项目启动时，对将所有用户状态更新为free
 */
@Component
public class InitApplicationRunner implements ApplicationRunner {
    @Resource
    private UserRepository repository;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        repository.updateStatus();
    }
}
