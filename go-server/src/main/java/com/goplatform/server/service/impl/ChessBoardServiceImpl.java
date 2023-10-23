package com.goplatform.server.service.impl;

import com.goplatform.server.pojo.constant.ChessBoardStatus;
import com.goplatform.server.pojo.constant.Player;
import com.goplatform.server.pojo.domain.ChessBoard;
import com.goplatform.server.pojo.domain.ChessBoardConfig;
import com.goplatform.server.pojo.domain.Result;
import com.goplatform.server.pojo.domain.Room;
import com.goplatform.server.service.ChessBoardService;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ChessBoardServiceImpl implements ChessBoardService {


    public ChessBoard createChessBoard(Long userId, long roomID, ChessBoardConfig chessBoardConfig) {

        ChessBoard chessBoard=new ChessBoard();
        chessBoard.setChessBoardId(chessBoardConfig.getChessBoardId());
        chessBoard.setStatus(ChessBoardStatus.Going);
        chessBoard.setNowPlayer(Player.BLACK_PLAYER);

        // TODO:找到room
        Room room=null;
        room.setChessBoardConfig(chessBoardConfig);
        room.setChessBoard(chessBoard);

        return chessBoard;
    }



    public Result enterChessBoard(Long userId, Long roomId) {

        // TODO:找到房间
        Room room=null;
        ChessBoardConfig chessBoardConfig=room.getChessBoardConfig();
        if(Objects.equals(chessBoardConfig.getBlackPlayerId(),null)){
            // 黑方没人
            chessBoardConfig.setBlackPlayerId(userId);
        }
        else if(Objects.equals(chessBoardConfig.getWhitePlayerId(),null)){
            // 黑方没人
            chessBoardConfig.setWhitePlayerId(userId);
        }
        // TODO:返回信息
        return null;
    }

    public Result exitChessBoard(Long userId, Long roomId) {
        Room room=null;
        ChessBoardConfig chessBoardConfig=room.getChessBoardConfig();
        if(Objects.equals(chessBoardConfig.getBlackPlayerId(),userId)){
            // 黑方没人
            chessBoardConfig.setBlackPlayerId(null);
        }
        else if(Objects.equals(chessBoardConfig.getWhitePlayerId(),userId)){
            // 黑方没人
            chessBoardConfig.setWhitePlayerId(null);
        }
        // TODO:返回信息
        return null;
    }

    public Result changeColor(Long userId, Long roomId, Long color) {

        // TODO:找到房间
        Room room=null;
        ChessBoardConfig chessBoardConfig=room.getChessBoardConfig();
        if(Objects.equals(color,Player.WHITE_PLAYER)){
            if(Objects.equals( chessBoardConfig.getWhitePlayerId(),userId)){

            }
            else{
                Long tempId=chessBoardConfig.getWhitePlayerId();
                chessBoardConfig.setWhitePlayerId(userId);
                chessBoardConfig.setBlackPlayerId(tempId);
            }
        }
        else if(Objects.equals(color,Player.BLACK_PLAYER)){
            if(Objects.equals(chessBoardConfig.getBlackPlayerId(),userId)){

            }
            else{
                Long tempId=chessBoardConfig.getBlackPlayerId();
                chessBoardConfig.setWhitePlayerId(tempId);
                chessBoardConfig.setBlackPlayerId(userId);
            }
        }
        // TODO:返回信息
        return null;
    }
}
