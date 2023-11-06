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
        boolean res = chessBoardService.doOneMove(0, 0, 0, chessBoard);
        System.out.println(res);
        System.out.println(chessBoard);
    }

    @Test
    void testTake() {
        ChessBoard chessBoard = new ChessBoard();
        ChessBoardConfig config = new ChessBoardConfig();
        config.init(new ChessBoardConfig(), 1L);
        chessBoard.init(config);
        boolean res = chessBoardService.doOneMove(0, 0, 0, chessBoard);
        res = chessBoardService.doOneMove(0, 1, 1, chessBoard);
        res = chessBoardService.doOneMove(3, 3, 0, chessBoard);
        res = chessBoardService.doOneMove(1, 0, 1, chessBoard);
        System.out.println(res);
        System.out.println(chessBoard);

    }
}