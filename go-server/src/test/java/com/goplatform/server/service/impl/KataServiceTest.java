package com.goplatform.server.service.impl;

import com.goplatform.server.pojo.domain.ChessBoard;
import com.goplatform.server.pojo.domain.ChessBoardConfig;
import com.goplatform.server.pojo.domain.KataCount;
import com.goplatform.server.service.KataService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class KataServiceTest {

    @Resource
    ChessBoardServiceImpl chessBoardService;

    @Resource
    KataService kataService;

    @Test
    void testEndCount() {
        ChessBoard chessBoard = new ChessBoard();
        ChessBoardConfig config = new ChessBoardConfig();
        config.init(new ChessBoardConfig(), 1L);
        chessBoard.init(config);
        boolean res = chessBoardService.doOneMove(0, 0, 0, chessBoard);
        res = chessBoardService.doOneMove(0, 1, 1, chessBoard);
        res = chessBoardService.doOneMove(1, 1, 0, chessBoard);
        res = chessBoardService.doOneMove(1, 0, 1, chessBoard);
        res = chessBoardService.doOneMove(2, 0, 0, chessBoard);
        res = chessBoardService.doOneMove(2, 1, 1, chessBoard);
        res = chessBoardService.doOneMove(0, 0, 0, chessBoard);
        res = chessBoardService.doOneMove(1, 0, 1, chessBoard);

        KataCount cnt = kataService.endCount(chessBoard.getBoard());
        System.out.println(cnt);
    }
}
