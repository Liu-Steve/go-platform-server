package com.goplatform.server.pojo.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Room {
    // TODO 房间属性确定

    // 创建用户Id
    private Long createUserId;
    // 创建用户名
    private String createUserName;
    // 棋盘
    private ChessBoard chessBoard;
    // 创建时间
    private Date createdate;
}
