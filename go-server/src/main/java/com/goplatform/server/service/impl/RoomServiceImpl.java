package com.goplatform.server.service.impl;

import com.goplatform.server.exception.ExceptionEnum;
import com.goplatform.server.exception.GoServerException;
import com.goplatform.server.manager.Scheduler;
import com.goplatform.server.pojo.domain.Room;
import com.goplatform.server.pojo.entity.UserEntity;
import com.goplatform.server.repository.UserRepository;
import com.goplatform.server.service.KataService;
import com.goplatform.server.service.RoomService;
import com.goplatform.server.utils.PublicUtil;
import com.goplatform.server.websocket.ChessWebSocketHandler;
import com.goplatform.server.websocket.WebSocketResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;


@Service
@Transactional
public class RoomServiceImpl implements RoomService {

    private static final Logger logger = LoggerFactory.getLogger(RoomServiceImpl.class);
    @Resource
    private UserRepository userRepository;
    @Resource
    private Scheduler scheduler;

    @Resource
    private KataService kataService;

    /**
     * 创建房间，此时还未创建棋盘
     *
     * @param userId 创建用户
     * @return 创建结果
     */
    public Room createRoom(Long userId) {
        ChessWebSocketHandler.checkWebSocketConnection(userId);
        // 检查用户是否合法
        UserEntity userEntity = checkAndGetUser(userId, UserEntity.USER_STATUS_FREE);
        // 创建房间
        Room roomNew = new Room();
        long roomID = PublicUtil.getUUID();
        roomNew.setRoomId(roomID);
        roomNew.setCreateUserId(userId);
        roomNew.setCreateUserName(userEntity.getUsername());
        roomNew.setPersonCount(1);
        roomNew.setCreatedate(new Date());
        scheduler.addRoom(roomNew, false);
        // 更新用户状态
        scheduler.addUserRoom(userId, roomNew);
        userRepository.updateStatusByUserId(UserEntity.USER_STATUS_BUSY, userId);
        return roomNew;
    }

    /**
     * 创建人机对战房间，并且AI用户进入房间
     *
     * @param userId 创建用户
     * @return 创建结果
     */
    @Override
    public Room createKataRoom(Long userId) {
        ChessWebSocketHandler.checkWebSocketConnection(userId);
        // 检查用户是否合法
        UserEntity userEntity = checkAndGetUser(userId, UserEntity.USER_STATUS_FREE);
        // 创建房间，并且AI进入房间
        Room roomNew = new Room();
        long roomID = PublicUtil.getUUID();
        roomNew.setRoomId(roomID);
        roomNew.setCreateUserId(userId);
        roomNew.setCreateUserName(userEntity.getUsername());
        roomNew.setSecondUserId(-1L);
        roomNew.setPersonCount(2);
        roomNew.setCreatedate(new Date());
        scheduler.addRoom(roomNew, true);
        // 更新用户状态
        scheduler.addUserRoom(userId, roomNew);
        userRepository.updateStatusByUserId(UserEntity.USER_STATUS_BUSY, userId);
        return roomNew;
    }

    /**
     * 进入房间，还未开始游戏
     *
     * @param userId 用户Id
     * @param roomId 加入的房间Id
     * @return 返回房间信息
     */
    public Room enterRoom(Long userId, Long roomId) {
        ChessWebSocketHandler.checkWebSocketConnection(userId);
        // 校验并获得用户信息
        checkAndGetUser(userId, UserEntity.USER_STATUS_FREE);
        if (scheduler.isKataRoom(roomId)) {
            throw new GoServerException(ExceptionEnum.ROOM_KATA_ROOM_ENTER_ERROR);
        }
        // 获得对应的房间
        Room room = scheduler.getRoom(roomId);
        if (room.getPersonCount() == 2) {
            throw new GoServerException(ExceptionEnum.ROOM_ALREADY_FULL);
        }
        // 更新房间信息
        room.setSecondUserId(userId);
        room.setPersonCount(2);
        // 更新用户状态
        scheduler.addUserRoom(userId, room);
        userRepository.updateStatusByUserId(UserEntity.USER_STATUS_BUSY, userId);
        // 通知房主
        ChessWebSocketHandler.sendResult(room.getCreateUserId(), room, WebSocketResult.ROOM_ENTER);
        return room;
    }

    /**
     * 退出房间
     * 1、如果房间只有一个人，则删除房间
     * 2、如果房间有两个人，非房主退出，正常维护
     * 3、如果房间有两个人，房主退出房间，则另一个人升级为房主
     * 4、如果是人机对战房间，则直接退出，并销毁房间
     *
     * @param userId 退出房间的用户Id
     * @param roomId 退出的房间Id
     * @return 退出结果
     */
    public Room exitRoom(Long userId, Long roomId) {
        // 鉴权：用户是否存在并且在房间中
        UserEntity userEntity = checkAndGetUser(userId, UserEntity.USER_STATUS_BUSY);
        Room room = scheduler.getRoom(roomId);
        Room resultRoom = null;
        if (!Objects.equals(room.getCreateUserId(), userId) && !Objects.equals(room.getSecondUserId(), userId)) {
            throw new GoServerException(ExceptionEnum.ROOM_USER_NOT_INSIDE, userEntity.getUsername(), roomId);
        }

        // 如果是人机对战房间，则直接退出，并销毁房间，销毁AI
        if (scheduler.isKataRoom(roomId)) {
            scheduler.removeRoom(roomId);
            scheduler.removeUserRoom(userId);
            kataService.destroy(roomId);
            userRepository.updateStatusByUserId(UserEntity.USER_STATUS_FREE, userId);
            return null;
        }
        // 如果房间只有一个人，则删除房间
        if (room.getPersonCount() == 1) {
            scheduler.removeRoom(roomId);
        }

        // 如果房间有两个人，非房主退出，正常维护
        if (room.getPersonCount() == 2 && room.getSecondUserId().equals(userId)) {
            // 更新房间状态
            room.setPersonCount(1);
            room.setSecondUserId(null);
            room.setChessBoardConfig(null);
            room.setChessBoard(null);
            resultRoom =  room;
        }

        // 如果房间有两个人，房主退出房间，则另一个人升级为房主
        if (room.getPersonCount() == 2 && room.getCreateUserId().equals(userId)) {
            // 更新房间状态
            Long anotherUserId = room.getSecondUserId();
            UserEntity anotherUser = checkAndGetUser(anotherUserId, UserEntity.USER_STATUS_BUSY);
            room.setCreateUserId(anotherUserId);
            room.setCreateUserName(anotherUser.getUsername());
            room.setSecondUserId(null);
            room.setPersonCount(1);
            room.setChessBoardConfig(null);
            room.setChessBoard(null);
            resultRoom = room;
        }
        // 更新用户状态
        scheduler.removeUserRoom(userId);
        userRepository.updateStatusByUserId(UserEntity.USER_STATUS_FREE, userId);

        // 通知房主
        if (resultRoom != null) {
            ChessWebSocketHandler.sendResult(resultRoom.getCreateUserId(), resultRoom, WebSocketResult.ROOM_EXIT);
        }
        return resultRoom;
    }

    @Override
    public Collection<Room> listRoom() {
        return scheduler.listRoom();
    }

    @Override
    public Room findRoomById(Long roomId) {
        return scheduler.getRoom(roomId);
    }

    public UserEntity checkAndGetUser(Long userId, int status) {
        PublicUtil.checkUserIdValid(userId, userRepository);
        UserEntity userEntity = userRepository.findById(userId).orElse(null);
        if (userEntity == null) {
            throw new GoServerException(ExceptionEnum.USER_USERID_INVALID);
        }
        if (userEntity.getStatus() != status) {
            throw new GoServerException(ExceptionEnum.USER_USER_STATUS_INVALID);
        }
        return userEntity;
    }
}
