package com.goplatform.server.service;

import com.goplatform.server.pojo.domain.Room;

import java.util.Collection;

public interface RoomService {

    
    Room createRoom(Long userId);
    Room createKataRoom(Long userId);

    Room enterRoom(Long userId, Long roomId);

    Room exitRoom(Long userId, Long roomId);

    Collection<Room> listRoom();

    Room findRoomById(Long roomId);
}
