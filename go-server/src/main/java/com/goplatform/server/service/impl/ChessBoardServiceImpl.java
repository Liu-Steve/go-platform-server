package com.goplatform.server.service.impl;

import com.goplatform.server.pojo.constant.ChessBoardStatus;
import com.goplatform.server.pojo.constant.Player;
import com.goplatform.server.pojo.domain.*;
import com.goplatform.server.service.ChessBoardService;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

@Service
public class ChessBoardServiceImpl implements ChessBoardService {


    public ChessBoard createChessBoard(Long userId, long roomID, ChessBoardConfig chessBoardConfig) {

        ChessBoard chessBoard=new ChessBoard();
        chessBoard.setChessBoardId(chessBoardConfig.getChessBoardId());
        chessBoard.setStatus(ChessBoardStatus.Going);
        chessBoard.setNowPlayer(Player.BLACK_PLAYER);

        // TODO:找到room
        Room room=null;
        room.setChessBoardConfig(chessBoardConfig);
        room.setChessBoard(chessBoard);

        return chessBoard;
    }



    public Result enterChessBoard(Long userId, Long roomId) {

        // TODO:找到房间
        Room room=null;
        ChessBoardConfig chessBoardConfig=room.getChessBoardConfig();
        if(Objects.equals(chessBoardConfig.getBlackPlayerId(),null)){
            // 黑方没人
            chessBoardConfig.setBlackPlayerId(userId);
        }
        else if(Objects.equals(chessBoardConfig.getWhitePlayerId(),null)){
            // 黑方没人
            chessBoardConfig.setWhitePlayerId(userId);
        }
        // TODO:返回信息
        return null;
    }

    public Result exitChessBoard(Long userId, Long roomId) {
        Room room=null;
        ChessBoardConfig chessBoardConfig=room.getChessBoardConfig();
        if(Objects.equals(chessBoardConfig.getBlackPlayerId(),userId)){
            // 黑方没人
            chessBoardConfig.setBlackPlayerId(null);
        }
        else if(Objects.equals(chessBoardConfig.getWhitePlayerId(),userId)){
            // 黑方没人
            chessBoardConfig.setWhitePlayerId(null);
        }
        // TODO:返回信息
        return null;
    }

    public Result changeColor(Long userId, Long roomId, Long color) {

        // TODO:找到房间
        Room room=null;
        ChessBoardConfig chessBoardConfig=room.getChessBoardConfig();
        if(Objects.equals(color,Player.WHITE_PLAYER)){
            if(Objects.equals( chessBoardConfig.getWhitePlayerId(),userId)){

            }
            else{
                Long tempId=chessBoardConfig.getWhitePlayerId();
                chessBoardConfig.setWhitePlayerId(userId);
                chessBoardConfig.setBlackPlayerId(tempId);
            }
        }
        else if(Objects.equals(color,Player.BLACK_PLAYER)){
            if(Objects.equals(chessBoardConfig.getBlackPlayerId(),userId)){

            }
            else{
                Long tempId=chessBoardConfig.getBlackPlayerId();
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
            if (nr < 0 || nr >= ChessBoard.BOARD_LEN ||
                    nc < 0 || nc >= ChessBoard.BOARD_LEN) {
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
        for (int i = 0; i < ChessBoard.BOARD_LEN; i++) {
            for (int j = 0; j < ChessBoard.BOARD_LEN; j++) {
                board.getBoardFlag()[i][j] = false;
            }
        }
    }

    public boolean isNoLiberty(int r, int c, ChessBoard board) {
        flushFlags(board);

        boolean ret = true;
        int type = board.getBoard()[r][c];
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {r, c});

        bfs: while (!q.isEmpty()) {
            int[] tmp = q.poll();
            int tmp_r = tmp[0], tmp_c = tmp[1];
            board.getBoardFlag()[tmp_r][tmp_c] = true;
            for (int i = 0; i < 4; i++) {
                int nr = tmp_r + dr[i], nc = tmp_c + dc[i];
                if (posIsNotValid(nr, nc, board)) {
                    continue ;
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
        q.offer(new int[] {r, c});

        while (!q.isEmpty()) {
            int[] tmp = q.poll();
            int tmp_r = tmp[0], tmp_c = tmp[1];
            board.getBoardFlag()[tmp_r][tmp_c] = true;
            board.getBoard()[tmp_r][tmp_c] = ChessBoard.EMPTY;
            for (int i = 0; i < 4; i++) {
                int nr = tmp_r + dr[i], nc = tmp_c + dc[i];
                if (posIsNotValid(nr, nc, board)) {
                    continue ;
                }
                if (board.getBoard()[nr][nc] == type) {
                    q.offer(new int[]{nr, nc});
                }
            }
        }
    }

    public boolean posIsNotValid(int r, int c, ChessBoard board) {
        if (r < 0 || r >= ChessBoard.BOARD_LEN ||
                c < 0 || c >= ChessBoard.BOARD_LEN) {
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
