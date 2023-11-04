package com.goplatform.server.service;

import com.goplatform.server.pojo.domain.ChessBoard;
import com.goplatform.server.pojo.domain.ChessBoardConfig;
import com.goplatform.server.pojo.domain.Result;
import com.goplatform.server.pojo.domain.Room;

public interface ChessBoardService {
    Room createChessBoard(Long userId, long roomID, ChessBoardConfig chessBoardConfig);


    Result enterChessBoard(Long userId, Long roomId);

    Result exitChessBoard(Long userId, Long roomId);

    Result changeColor(Long userId, Long roomId, Long color);
}
