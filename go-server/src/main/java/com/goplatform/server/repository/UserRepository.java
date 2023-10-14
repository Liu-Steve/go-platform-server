package com.goplatform.server.repository;

import com.goplatform.server.pojo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findUserEntityByUsername(String name);

    UserEntity findUserEntityByEmail(String email);

}
