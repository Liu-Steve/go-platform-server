package com.goplatform.server.repository;

import com.goplatform.server.pojo.entity.GameLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface GameRepository extends JpaRepository<GameLogEntity, Long> {
}
