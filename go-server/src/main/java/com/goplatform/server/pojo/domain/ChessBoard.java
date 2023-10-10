package com.goplatform.server.pojo.domain;

import lombok.Data;

@Data
public class ChessBoard {
    //TODO 棋盘属性确定

    // 创建用户ID
    private Long createUserId;
    // 创建用户名
    private String createUserName;
    // 白色方棋手
    private Long whitePlayerId;
    // 黑色方棋手
    private Long blackPlayerId;
    // 棋盘大小
    private Long boardSize = 19L;
    // 用于表示具体棋盘
    private String[][] board;
    // 棋盘状态 0：未开始 1：对局中 2：请求结束 3：结束（后续枚举类）
    private Long status;
    // 当前应该谁下棋 0：白色 1：黑色 （后续枚举类）
    private Long nowPlayer;
}
