package com.goplatform.server.repository;

import com.goplatform.server.pojo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

@Transactional
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findUserEntityByUsername(String name);

    UserEntity findUserEntityByEmail(String email);

    @Modifying
    @Query("update UserEntity user set user.status=:newStatus where user.id=:userId")
    int updateStatusByUserId(@Param("newStatus") int status, @Param("userId") Long userId);

    @Modifying
    @Query("update UserEntity user set user.status=0")
    int updateStatus();
}
