package com.goplatform.server.service.impl;

import com.goplatform.server.controller.ChessBoardController;
import com.goplatform.server.pojo.domain.ChessBoard;
import com.goplatform.server.pojo.domain.ChessBoardConfig;
import com.goplatform.server.pojo.domain.Result;
import com.goplatform.server.pojo.domain.Room;
import com.goplatform.server.service.RoomService;
import com.goplatform.server.utils.PublicUtil;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Date;


@Service
@Transactional
public class RoomServiceImpl implements RoomService {

    private Room roomRec;

    public Room createRoom(Long userId,Room room){

        // TODO:鉴权


        // 创建房间
        roomRec=room;
        long roomID= PublicUtil.getUUID();
        room.setRoomId(roomID);
        room.setCreateUserId(userId);
        // TODO:得到用户名？？？
        room.setCreateUserName("");
        //绑定棋盘
        ChessBoardConfig chessBoardConfig = new ChessBoardConfig();
        room.setChessBoardConfig(chessBoardConfig);
        Result chessBoardRes= chessBoardController.createChessBoard(userId,roomID,chessBoardConfig);
        room.setChessBoard((ChessBoard) chessBoardRes.getResult());
        room.setCreatedate(new Date());


        return null;
    }


    public Result enterRoom(Long userId, Long roomId) {
        // TODO:鉴权

        //进入房间，绑定信息，加入棋盘
        //Result enterRes=new Result();
        //enterRes=chessBoardController.enterChessBoard(userId);

        return null;
    }


    public Result exitRoom(Long userId, Long roomId) {
        // TODO:鉴权

        //退出房间
        Result exitRes;
        exitRes=chessBoardController.overGameConfirm(userId,roomId);
        return exitRes;
    }

}
