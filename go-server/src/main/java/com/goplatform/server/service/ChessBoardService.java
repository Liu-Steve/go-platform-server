package com.goplatform.server.service;

import com.goplatform.server.pojo.domain.ChessBoard;
import com.goplatform.server.pojo.domain.ChessBoardConfig;
import com.goplatform.server.pojo.domain.ChessDrop;
import com.goplatform.server.pojo.domain.Result;
import com.goplatform.server.pojo.domain.Room;

public interface ChessBoardService {
    Room createChessBoard(Long userId, Long roomID, ChessBoardConfig chessBoardConfig);

    ChessBoard dropChess(Long userId, Long roomId, ChessDrop chessDrop);

    ChessBoard stopOnce(Long userId, Long roomId);

    ChessBoard overGameRequest(Long userId, Long roomId);

    ChessBoard overGameConfirm(Long userId, Long roomId);

    Object getChessBoardResult(Long userId, Long roomId);
}
