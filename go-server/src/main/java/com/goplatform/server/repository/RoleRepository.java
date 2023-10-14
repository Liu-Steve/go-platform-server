package com.goplatform.server.repository;

import com.goplatform.server.pojo.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface RoleRepository extends JpaRepository<RoleEntity, String> {

    RoleEntity findRoleEntityByName(String name);

}
