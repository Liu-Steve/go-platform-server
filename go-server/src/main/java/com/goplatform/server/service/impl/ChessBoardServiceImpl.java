package com.goplatform.server.service.impl;

import com.goplatform.server.exception.ExceptionEnum;
import com.goplatform.server.exception.GoServerException;
import com.goplatform.server.manager.Scheduler;
import com.goplatform.server.pojo.constant.ChessBoardStatus;
import com.goplatform.server.pojo.constant.Player;
import com.goplatform.server.pojo.domain.*;
import com.goplatform.server.repository.UserRepository;
import com.goplatform.server.service.ChessBoardService;
import com.goplatform.server.utils.PublicUtil;
import com.goplatform.server.websocket.ChessWebSocketHandler;
import com.goplatform.server.websocket.WebSocketResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

@Service
public class ChessBoardServiceImpl implements ChessBoardService {

    @Resource
    private UserRepository userRepository;
    @Resource
    private Scheduler scheduler;

    @Override
    public Room createChessBoard(Long userId, long roomId, ChessBoardConfig chessBoardConfig) {

        // 1、判断用户是否有权限来创建棋盘
        PublicUtil.checkUserIdValid(userId, userRepository);
        Room room = scheduler.getRoom(roomId);
        if (!room.getCreateUserId().equals(userId)) {
            throw new GoServerException(ExceptionEnum.CHESS_USER_PERMISSION_DENY);
        }

        // 2、补全棋盘配置，并放到room里面
        ChessBoardConfig config = new ChessBoardConfig();
        config.init(chessBoardConfig, roomId);
        room.setChessBoardConfig(config);

        // 3、初始化棋盘
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.init(config);
        room.setChessBoard(chessBoard);
        // 4、通知白方下棋，黑方等待
        ChessWebSocketHandler.sendResult(config.getWhitePlayerId(), chessBoard, WebSocketResult.CHESS_START);
        ChessWebSocketHandler.sendResult(config.getBlackPlayerId(), chessBoard, WebSocketResult.CHESS_WAIT);

        return room;
    }



    public Result enterChessBoard(Long userId, Long roomId) {

        // TODO:找到房间
        Room room = null;
        ChessBoardConfig chessBoardConfig = room.getChessBoardConfig();
        if (Objects.equals(chessBoardConfig.getBlackPlayerId(), null)) {
            // 黑方没人
            chessBoardConfig.setBlackPlayerId(userId);
        } else if (Objects.equals(chessBoardConfig.getWhitePlayerId(), null)) {
            // 黑方没人
            chessBoardConfig.setWhitePlayerId(userId);
        }
        // TODO:返回信息
        return null;
    }

    public Result exitChessBoard(Long userId, Long roomId) {
        Room room = null;
        ChessBoardConfig chessBoardConfig = room.getChessBoardConfig();
        if (Objects.equals(chessBoardConfig.getBlackPlayerId(), userId)) {
            // 黑方没人
            chessBoardConfig.setBlackPlayerId(null);
        } else if (Objects.equals(chessBoardConfig.getWhitePlayerId(), userId)) {
            // 黑方没人
            chessBoardConfig.setWhitePlayerId(null);
        }
        // TODO:返回信息
        return null;
    }

    public Result changeColor(Long userId, Long roomId, Long color) {

        // TODO:找到房间
        Room room = null;
        ChessBoardConfig chessBoardConfig = room.getChessBoardConfig();
        if (Objects.equals(color, Player.WHITE_PLAYER)) {
            if (Objects.equals(chessBoardConfig.getWhitePlayerId(), userId)) {

            } else {
                Long tempId = chessBoardConfig.getWhitePlayerId();
                chessBoardConfig.setWhitePlayerId(userId);
                chessBoardConfig.setBlackPlayerId(tempId);
            }
        } else if (Objects.equals(color, Player.BLACK_PLAYER)) {
            if (Objects.equals(chessBoardConfig.getBlackPlayerId(), userId)) {

            } else {
                Long tempId = chessBoardConfig.getBlackPlayerId();
                chessBoardConfig.setWhitePlayerId(tempId);
                chessBoardConfig.setBlackPlayerId(userId);
            }
        }
        // TODO:返回信息
        return null;
    }

    private static final int[] dr = {-1, 0, 0, 1};
    private static final int[] dc = {0, -1, 1, 0};

    private int turnPlayerIntoChessboardColor(Player player) {
        if (player == Player.BLACK_PLAYER) {
            return ChessBoard.BLACK;
        } else {
            return ChessBoard.WHITE;
        }
    }

    private Player turnChessboardColorIntoPlayer(int player) {
        if (player == ChessBoard.WHITE) {
            return Player.WHITE_PLAYER;
        } else {
            return Player.BLACK_PLAYER;
        }
    }

    public boolean tryTake(int r, int c, ChessBoard board) { // if killed successfully, modify the board directly
        boolean ret = false;
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i], nc = r + dc[i];
            if (nr < 0 || nr >= board.getBoardSize() ||
                    nc < 0 || nc >= board.getBoardSize()) {
                continue;
            }
            if (isNoLiberty(nr, nc, board)) {
                take(nr, nc, board);
                ret = true;
            }
        }
        return ret;
    }

    private void flushFlags(ChessBoard board) {
        for (int i = 0; i < board.getBoardSize(); i++) {
            Arrays.fill(board.getBoardFlag()[i], false);
        }
    }

    public boolean isNoLiberty(int r, int c, ChessBoard board) {
        flushFlags(board);

        boolean ret = true;
        int type = board.getBoard()[r][c];
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{r, c});

        bfs:
        while (!q.isEmpty()) {
            int[] tmp = q.poll();
            int tmp_r = tmp[0], tmp_c = tmp[1];
            board.getBoardFlag()[tmp_r][tmp_c] = true;
            for (int i = 0; i < 4; i++) {
                int nr = tmp_r + dr[i], nc = tmp_c + dc[i];
                if (posIsNotValid(nr, nc, board)) {
                    continue;
                }
                if (board.getBoard()[nr][nc] == type) {
                    q.offer(new int[]{nr, nc});
                } else if (board.getBoard()[nr][nc] == ChessBoard.EMPTY) {
                    ret = false;
                    break bfs;
                }
            }
        }
        return ret;
    }

    public void take(int r, int c, ChessBoard board) {
        flushFlags(board);

        int type = board.getBoard()[r][c];
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{r, c});

        while (!q.isEmpty()) {
            int[] tmp = q.poll();
            int tmp_r = tmp[0], tmp_c = tmp[1];
            board.getBoardFlag()[tmp_r][tmp_c] = true;
            board.getBoard()[tmp_r][tmp_c] = ChessBoard.EMPTY;
            for (int i = 0; i < 4; i++) {
                int nr = tmp_r + dr[i], nc = tmp_c + dc[i];
                if (posIsNotValid(nr, nc, board)) {
                    continue;
                }
                if (board.getBoard()[nr][nc] == type) {
                    q.offer(new int[]{nr, nc});
                }
            }
        }
    }

    public boolean posIsNotValid(int r, int c, ChessBoard board) {
        if (r < 0 || r >= board.getBoardSize() ||
                c < 0 || c >= board.getBoardSize()) {
            return true;
        }
        return board.getBoardFlag()[r][c];
    }

    public boolean doOneMove(int r, int c, int type, ChessBoard board) {
        if (type != turnPlayerIntoChessboardColor(board.getNowPlayer())) {
            // not this player's turn to move
            return false;
        }
        if (board.getBoard()[r][c] != ChessBoard.EMPTY) {
            // this pos has been taken
            return false;
        }
        boolean isValidMove = false;
        board.getBoard()[r][c] = type;
        if (tryTake(r, c, board)) {
            isValidMove = true;
        }
        if (!isNoLiberty(r, c, board)) {
            isValidMove = true;
        }
        if (isValidMove) {
            board.setNowPlayer(
                    turnChessboardColorIntoPlayer(1 -
                            turnPlayerIntoChessboardColor(board.getNowPlayer())
                    ));
        } else {
            board.getBoard()[r][c] = ChessBoard.EMPTY;
        }
        return isValidMove;
    }
}
