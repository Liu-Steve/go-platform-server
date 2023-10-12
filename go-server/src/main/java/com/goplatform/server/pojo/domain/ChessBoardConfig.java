package com.goplatform.server.pojo.domain;

import lombok.Data;

@Data
public class ChessBoardConfig {
    // 棋盘Id
    private Long chessBoardId;
    // 房间Id
    private Long roomId;
    // 白色方棋手
    private Long whitePlayerId;
    // 黑色方棋手
    private Long blackPlayerId;
    // 棋盘大小
    private Long boardSize = 19L;
    // 下棋时间
    private Long timeToDrop;
}
