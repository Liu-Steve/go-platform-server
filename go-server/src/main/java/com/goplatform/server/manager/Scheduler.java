package com.goplatform.server.manager;

import com.goplatform.server.exception.ExceptionEnum;
import com.goplatform.server.exception.GoServerException;
import com.goplatform.server.pojo.domain.Room;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Scheduler用于管理房间的创建和存储
 * 是房间的管理者，该类应为单例模式，并在项目启动的时候实例化
 */
@Component
public class Scheduler {
    // 用于存储创建的房间，键为房间Id，值为具体房间
    private final ConcurrentHashMap<Long, Room> roomMaps = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<Long, Room> userRoomMaps = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<Long, Room> kataRoomMaps = new ConcurrentHashMap<>();

    private static final Logger logger = LoggerFactory.getLogger(Scheduler.class);

    public Room addRoom(Room room, Boolean isKata) {
        Long roomId = room.getRoomId();
        if (isKata) {
            logger.debug("add kata room {}", roomId);
            return kataRoomMaps.put(roomId, room);
        }
        logger.debug("add room {}", roomId);
        return roomMaps.put(roomId, room);
    }

    public Room getRoom(Long roomId) {
        // 校验RoomId是否正确
        if (roomId == null) {
            throw new GoServerException(ExceptionEnum.ROOM_ID_INVALID, "null");
        }
        if (roomMaps.containsKey(roomId) && roomMaps.get(roomId) != null) {
            return roomMaps.get(roomId);
        }
        if (kataRoomMaps.containsKey(roomId) && kataRoomMaps.get(roomId) != null) {
            return kataRoomMaps.get(roomId);
        }
        throw new GoServerException(ExceptionEnum.ROOM_ID_INVALID, roomId);
    }

    public Room removeRoom(Long roomId) {
        // 校验RoomId是否正确
        if (roomId == null) {
            throw new GoServerException(ExceptionEnum.ROOM_ID_INVALID, "null");
        }
        if (roomMaps.containsKey(roomId)) {
            logger.debug("remove room {}", roomId);
            return roomMaps.remove(roomId);
        }
        if (kataRoomMaps.containsKey(roomId)) {
            logger.debug("remove kata room {}", roomId);
            return kataRoomMaps.remove(roomId);
        }
        return null;
    }

    public Collection<Room> listRoom() {
        return roomMaps.values();
    }

    public Collection<Room> listKataRoom() {
        return kataRoomMaps.values();
    }

    public boolean isKataRoom(Long roomId) {
        return kataRoomMaps.containsKey(roomId);
    }

    public Room getUserRoom(Long userId) {
        if (userRoomMaps.containsKey(userId)) {
            return userRoomMaps.get(userId);
        }
        return null;
    }

    public Room addUserRoom(Long userId, Room room) {
        logger.debug("add user-room mapping user: {}, room: {}", userId, room.getRoomId());
        return userRoomMaps.put(userId, room);
    }

    public Room removeUserRoom(Long userId) {
        if (userId == null || !userRoomMaps.containsKey(userId) || userRoomMaps.get(userId) == null) {
            return null;
        }
        logger.debug("remove user-room mapping user: {}, room: {}", userId, userRoomMaps.get(userId).getRoomId());
        return userRoomMaps.remove(userId);
    }
}
