package com.goplatform.server.service;

import com.goplatform.server.controller.ChessBoardController;
import com.goplatform.server.pojo.domain.Result;
import com.goplatform.server.pojo.domain.Room;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

import javax.annotation.Resource;

public interface RoomService {

    
    public Room createRoom(Long userId,Room room);

    Result enterRoom(Long userId, Long roomId);

    Result exitRoom(Long userId, Long roomId);
}
