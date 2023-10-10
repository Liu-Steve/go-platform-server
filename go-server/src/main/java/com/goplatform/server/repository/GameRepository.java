package com.goplatform.server.repository;

import com.goplatform.server.pojo.entity.GameLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<GameLogEntity, Long> {
}
