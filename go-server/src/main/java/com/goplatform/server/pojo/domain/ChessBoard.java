package com.goplatform.server.pojo.domain;

import com.goplatform.server.pojo.constant.ChessBoardStatus;
import com.goplatform.server.pojo.constant.Player;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ConcurrentLinkedQueue;

@Data
public class ChessBoard {
    //TODO 棋盘属性确定
    public static int EMPTY = -1, WHITE = 0, BLACK = 1;
    // 棋盘Id
    private Long chessBoardId;
    // 棋盘大小
    private int boardSize;
    // 用于表示具体棋盘
    private int[][] board;
    // 用于棋盘搜索过程中记录已经搜索的位置
    private boolean[][] boardFlag;
    // 棋盘状态 0：未开始 1：对局中 2：请求结束 3：结束（后续枚举类）
    private ChessBoardStatus status;
    // 落子记录
    private ConcurrentLinkedQueue<OneMove> record;
    // 当前应该谁下棋 0：白色 1：黑色 （后续枚举类）
    private Player nowPlayer;

    public void init(ChessBoardConfig config) {
        boardSize = config.getBoardSize();
        chessBoardId = config.getChessBoardId();
        board = new int[boardSize][boardSize];
        for (int i = 0; i < boardSize; i ++ ) {
            Arrays.fill(board[i], ChessBoard.EMPTY);
        }
        record = new ConcurrentLinkedQueue<>();
        nowPlayer = Player.WHITE_PLAYER;
        status = ChessBoardStatus.Going;
    }
    public void flushFlag() {
        boardFlag = new boolean[boardSize][boardSize];
        for (int i = 0; i < boardSize; i ++ ) {
            Arrays.fill(boardFlag[i], false);
        }
    }
}
