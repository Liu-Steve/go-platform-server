package com.goplatform.server.pojo.domain;

import com.goplatform.server.utils.PublicUtil;
import lombok.Data;

@Data
public class ChessBoardConfig {
    private static final int DEFAULT_BOARD_SIZE = 19;
    private static final int DEFAULT_TIME_TO_DROP = 60;
    // 棋盘Id
    private Long chessBoardId;
    // 房间Id
    private Long roomId;
    // 白色方棋手
    private Long whitePlayerId;
    // 黑色方棋手
    private Long blackPlayerId;
    // 棋盘大小
    private int boardSize;
    // 下棋时间
    private int timeToDrop;

    public void init(ChessBoardConfig chessBoardConfig, Long roomId) {
        setChessBoardId(PublicUtil.getUUID());
        setRoomId(roomId);
        setWhitePlayerId(chessBoardConfig.getWhitePlayerId());
        setBlackPlayerId(chessBoardConfig.getBlackPlayerId());
        int boardSizeNew = chessBoardConfig.getBoardSize();
        if (boardSizeNew > 0 && boardSizeNew <= 25) {
            boardSize = boardSizeNew;
        } else {
            boardSize = DEFAULT_BOARD_SIZE;
        }
        int timeToDropNew = chessBoardConfig.getTimeToDrop();
        if (timeToDropNew >= 10  && timeToDropNew <= 120) {
            timeToDrop = timeToDropNew;
        }else {
            timeToDrop = DEFAULT_TIME_TO_DROP;
        }
    }
}
