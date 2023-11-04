package com.goplatform.server.pojo.domain;

import lombok.Data;

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

    public Long getChessBoardId() {
        return chessBoardId;
    }

    public void setChessBoardId(Long chessBoardId) {
        this.chessBoardId = chessBoardId;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public Long getWhitePlayerId() {
        return whitePlayerId;
    }

    public void setWhitePlayerId(Long whitePlayerId) {
        this.whitePlayerId = whitePlayerId;
    }

    public Long getBlackPlayerId() {
        return blackPlayerId;
    }

    public void setBlackPlayerId(Long blackPlayerId) {
        this.blackPlayerId = blackPlayerId;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public void setBoardSize(int boardSize) {
        if (boardSize > 0 && boardSize <= 25) {
            this.boardSize = boardSize;
        } else {
            this.boardSize = DEFAULT_BOARD_SIZE;
        }
    }

    public int getTimeToDrop() {
        return timeToDrop;
    }

    public void setTimeToDrop(int timeToDrop) {
        if (timeToDrop >= 10  && timeToDrop <= 120) {
            this.timeToDrop = timeToDrop;
        }else {
            this.timeToDrop = DEFAULT_TIME_TO_DROP;
        }
    }
}
