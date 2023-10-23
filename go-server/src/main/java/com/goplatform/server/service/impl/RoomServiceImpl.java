package com.goplatform.server.service.impl;

import com.goplatform.server.controller.ChessBoardController;
import com.goplatform.server.manager.Scheduler;
import com.goplatform.server.pojo.domain.ChessBoard;
import com.goplatform.server.pojo.domain.ChessBoardConfig;
import com.goplatform.server.pojo.domain.Result;
import com.goplatform.server.pojo.domain.Room;
import com.goplatform.server.service.ChessBoardService;
import com.goplatform.server.service.RoomService;
import com.goplatform.server.utils.PublicUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.Objects;


@Service
@Transactional
public class RoomServiceImpl implements RoomService {


    private ChessBoardService chessBoardService;

    public Room createRoom(Long userId,Room room){

        // TODO:鉴权


        // 创建房间
        long roomID= PublicUtil.getUUID();
        room.setRoomId(roomID);
        room.setCreateUserId(userId);
        room.setPersonCount(1);
        // TODO:得到用户名？？？
        room.setCreateUserName("");
        room.setCreatedate(new Date());


        return room;
    }


    public Result enterRoom(Long userId, Long roomId) {
        // TODO:鉴权

        //进入房间，绑定信息，加入棋盘
        // TODO:找到room
        Room room=null;
        room.setSecondUserId(userId);
        room.setPersonCount(2);
        //加入房间时就是要加入棋盘
        Result enterRes=chessBoardService.enterChessBoard(userId,roomId);

        // TODO:返回信息
        return null;
    }


    public Result exitRoom(Long userId, Long roomId) {
        // TODO:鉴权

        //退出房间
        // TODO:找到room
        Room room=null;
        switch (room.getPersonCount()){
            case 0:
                // TODO:抛出异常，房间没有人了还有退出的
                break;
            case 1:
                // TODO:销毁房间和棋局
                break;
            case 2:
                if(Objects.equals(userId, room.getCreateUserId())) {
                    room.setCreateUserId(room.getSecondUserId());
                }
                room.setSecondUserId(null);
                room.setPersonCount(1);
                break;
        }
        //退出房间时就是要退出棋盘
        Result exitRes=chessBoardService.exitChessBoard(userId,roomId);
        return exitRes;
    }

}
