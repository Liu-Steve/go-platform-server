package com.goplatform.server.pojo.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Room {
    // TODO 房间属性确定

    // 房间Id
    private Long roomId;
    // 创建用户Id
    private Long createUserId;
    // 创建用户名
    private String createUserName;
    // 棋盘
    private ChessBoard chessBoard;
    // 棋盘配置
    private ChessBoardConfig chessBoardConfig;
    // 创建时间
    private Date createdate;
    // 房间人数
    private int personCount;
}
