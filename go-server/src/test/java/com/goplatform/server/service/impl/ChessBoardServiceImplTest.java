package com.goplatform.server.service.impl;

import com.goplatform.server.pojo.domain.ChessBoard;
import com.goplatform.server.pojo.domain.ChessBoardConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ChessBoardServiceImplTest {

    @Resource
    ChessBoardServiceImpl chessBoardService;
    @Test
    void doOneMove() {
        ChessBoard chessBoard = new ChessBoard();
        ChessBoardConfig config = new ChessBoardConfig();
        config.init(new ChessBoardConfig(), 1L);
        chessBoard.init(config);
        boolean res = chessBoardService.doOneMove(0, 0, 1, chessBoard);
        System.out.println(res);
        System.out.println(chessBoard);
    }

    @Test
    void testTake() {
        ChessBoard chessBoard = new ChessBoard();
        ChessBoardConfig config = new ChessBoardConfig();
        config.init(new ChessBoardConfig(), 1L);
        chessBoard.init(config);
        boolean res = chessBoardService.doOneMove(0, 0, 1, chessBoard);
        res = chessBoardService.doOneMove(0, 1, 0, chessBoard);
        res = chessBoardService.doOneMove(3, 3, 1, chessBoard);
        res = chessBoardService.doOneMove(1, 0, 0, chessBoard);
        System.out.println(res);
        System.out.println(chessBoard);
    }

    @Test
    void testTakeTwo() {
        ChessBoard chessBoard = new ChessBoard();
        ChessBoardConfig config = new ChessBoardConfig();
        config.init(new ChessBoardConfig(), 1L);
        chessBoard.init(config);
        boolean res = chessBoardService.doOneMove(0, 0, 1, chessBoard);
        res = chessBoardService.doOneMove(0, 2, 0, chessBoard);
        res = chessBoardService.doOneMove(0, 1, 1, chessBoard);
        res = chessBoardService.doOneMove(1, 0, 0, chessBoard);
        res = chessBoardService.doOneMove(3, 3, 1, chessBoard);
        res = chessBoardService.doOneMove(1, 1, 0, chessBoard);

        System.out.println(res);
        System.out.println(chessBoard);
    }

    @Test
    void testSimpleKo() {
        ChessBoard chessBoard = new ChessBoard();
        ChessBoardConfig config = new ChessBoardConfig();
        config.init(new ChessBoardConfig(), 1L);
        chessBoard.init(config);
        boolean res = chessBoardService.doOneMove(0, 0, 1, chessBoard);
        res = chessBoardService.doOneMove(0, 1, 0, chessBoard);
        res = chessBoardService.doOneMove(1, 1, 1, chessBoard);
        res = chessBoardService.doOneMove(1, 0, 0, chessBoard);
        res = chessBoardService.doOneMove(2, 0, 1, chessBoard);
        res = chessBoardService.doOneMove(2, 1, 0, chessBoard);
        res = chessBoardService.doOneMove(0, 0, 1, chessBoard);
        res = chessBoardService.doOneMove(1, 0, 0, chessBoard);

        System.out.println(res);
        System.out.println(chessBoard);
    }

    @Test
    void testYASimpleKo() {
        ChessBoard chessBoard = new ChessBoard();
        ChessBoardConfig config = new ChessBoardConfig();
        config.init(new ChessBoardConfig(), 1L);
        chessBoard.init(config);
        int[][] bd = chessBoard.getBoard();
        bd[0][1] = bd[1][0] = bd[2][1] = 1;
        bd[0][2] = bd[1][1] = bd[1][3] = bd[2][2] = 0;
        boolean res = chessBoardService.doOneMove(1, 2, 1, chessBoard);
        res = chessBoardService.doOneMove(1, 1, 0, chessBoard);

        System.out.println(res);
        System.out.println(chessBoard);
    }

    @Test
    void testSnapBack() {
        ChessBoard chessBoard = new ChessBoard();
        ChessBoardConfig config = new ChessBoardConfig();
        config.init(new ChessBoardConfig(), 1L);
        chessBoard.init(config);
        int[][] bd = chessBoard.getBoard();
        bd[0][1] = bd[1][1] = bd[2][0] = 1;
        bd[0][2] = bd[1][2] = bd[2][1] = 0;
        boolean res = chessBoardService.doOneMove(18, 18, 1, chessBoard);
        res = chessBoardService.doOneMove(1, 0, 0, chessBoard);
        res = chessBoardService.doOneMove(0, 0, 1, chessBoard);
        res = chessBoardService.doOneMove(1, 0, 0, chessBoard);

        System.out.println(res);
    }
}