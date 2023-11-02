package com.goplatform.server.service.impl;

import com.goplatform.server.exception.ExceptionEnum;
import com.goplatform.server.exception.GoServerException;
import com.goplatform.server.manager.Scheduler;
import com.goplatform.server.pojo.domain.Room;
import com.goplatform.server.pojo.entity.UserEntity;
import com.goplatform.server.repository.UserRepository;
import com.goplatform.server.service.ChessBoardService;
import com.goplatform.server.service.RoomService;
import com.goplatform.server.utils.PublicUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;


@Service
@Transactional
public class RoomServiceImpl implements RoomService {


    @Resource
    private ChessBoardService chessBoardService;
    @Resource
    private UserRepository userRepository;
    @Resource
    private Scheduler scheduler;

    /**
     * 创建房间，此时还未创建棋盘
     *
     * @param userId
     * @return
     */
    public Room createRoom(Long userId) {

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
        scheduler.addRoom(roomNew);
        // 更新用户状态
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
        // 校验并获得用户信息
        UserEntity userEntity = checkAndGetUser(userId, UserEntity.USER_STATUS_FREE);
        // 获得对应的房间
        Room room = scheduler.getRoom(roomId);

        // 更新房间信息
        room.setSecondUserId(userId);
        room.setPersonCount(2);
        // 更新用户状态
        userRepository.updateStatusByUserId(UserEntity.USER_STATUS_BUSY, userId);
        return room;
    }

    /**
     * 退出房间
     * 1、如果房间只有一个人，则删除房间
     * 2、如果房间有两个人，非房主退出，正常维护
     * 3、如果房间有两个人，房主退出房间，则另一个人升级为房主
     *
     * @param userId 退出房间的用户Id
     * @param roomId 退出的房间Id
     * @return 退出结果
     */
    public Room exitRoom(Long userId, Long roomId) {
        // TODO:鉴权：用户是否存在并且在房间中
        UserEntity userEntity = checkAndGetUser(userId, UserEntity.USER_STATUS_BUSY);
        Room room = scheduler.getRoom(roomId);
        if (!Objects.equals(room.getCreateUserId(), userId) && !Objects.equals(room.getSecondUserId(), userId)) {
            throw new GoServerException(ExceptionEnum.ROOM_USER_NOT_INSIDE, userEntity.getUsername(), roomId);
        }

        // 如果房间只有一个人，则删除房间
        if (room.getPersonCount() == 1) {
            scheduler.removeRoom(roomId);
            return null;
        }

        // 如果房间有两个人，非房主退出，正常维护
        if (room.getPersonCount() == 2 && room.getSecondUserId().equals(userId)) {
            // 更新房间状态
            room.setPersonCount(1);
            room.setSecondUserId(null);
            room.setChessBoardConfig(null);
            room.setChessBoard(null);
            // 更新用户状态
            userRepository.updateStatusByUserId(UserEntity.USER_STATUS_FREE, userId);
            return room;
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
            return room;
        }

        return null;
    }

    @Override
    public Collection<Room> listRoom() {
        return scheduler.listRoom();
    }

    @Override
    public Room findRoomById(Long roomId) {
        return scheduler.getRoom(roomId);
    }

    private UserEntity checkAndGetUser(Long userId, int status) {
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
