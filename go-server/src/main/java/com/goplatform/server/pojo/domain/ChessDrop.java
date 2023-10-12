package com.goplatform.server.pojo.domain;

import lombok.Data;

import java.util.List;

/**
 * 落子时前端向后端传入数据的包装
 */
@Data
public class ChessDrop {
    // TODO 具体的配置

    // 棋盘Id
    private Long roomId;
    // 下棋位置
    private List<Integer> dropPosition;
    // 下棋方 0代表白色方，1代表黑色方
    private int player;
    // 下棋方Id
    private Long playerId;
}
