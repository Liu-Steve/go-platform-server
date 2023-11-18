package com.goplatform.server.controller;

import com.goplatform.server.pojo.domain.*;
import com.goplatform.server.service.ChessBoardService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 棋盘接口
 * 负责：棋盘的配置，下棋的逻辑，悔棋的逻辑，棋盘当前胜负状态逻辑
 * 黑白两方都应该在创建房间或者进入房间时建立websocket连接
 */
@RestController
@RequestMapping("${apiPrefix}/chessBoard")
public class ChessBoardController {
    @Resource
    private ChessBoardService chessBoardService;

    /**
     * 配置棋盘信息，棋盘大小，等待时间等，并根据配置创建棋盘
     * 创建完棋盘后，开始游戏，白方先走并计时
     *
     * @param userId           房主Id
     * @param roomId           房间Id
     * @param chessBoardConfig 棋盘配置
     * @return 配置结果
     */
    @PostMapping("/{userId}/{roomId}")
    public Result createChessBoard(@PathVariable(value = "userId") Long userId,
                                   @PathVariable(value = "roomId") Long roomId,
                                   @RequestBody ChessBoardConfig chessBoardConfig) {
        /*
            1、在此之前黑白两方前端要与后端建立websocket通信
            2、根据前端信息初始化棋盘和棋盘配置
            3、通过websocket通知白方落子，黑方等待，并更新棋盘状态
            4、为白方初始化计时器任务
         */
        Room room = chessBoardService.createChessBoard(userId, roomId, chessBoardConfig);
        // TODO:返回信息
        return Result.ok(room);
    }

    /**
     * 查看当前棋盘信息
     *
     * @param userId 房主Id
     * @param roomId 房间Id
     * @return 棋盘信息
     */
    @GetMapping("/{userId}/{roomId}")
    public Result getChessBoardInfo(@PathVariable(value = "userId") Long userId,
                                    @PathVariable(value = "roomId") Long roomId) {
        // TODO 查询当前棋盘信息，返回查询结果
        return null;
    }

    /**
     * 落子逻辑
     *
     * @param userId    落子用户Id
     * @param roomId    房间Id
     * @param chessDrop 落子信息
     * @return 棋盘信息
     */
    @PostMapping("/drops/{userId}/{roomId}")
    public Result dropChess(@PathVariable(value = "userId") Long userId,
                            @PathVariable(value = "roomId") Long roomId,
                            @RequestBody ChessDrop chessDrop) {
        // TODO 落子的逻辑，返回棋盘的状态
        /*
         * 1、吃子
         * 2、更新棋盘
         * 3、下满了
         */
        ChessBoard chessBoard = chessBoardService.dropChess(userId, roomId, chessDrop);
        return Result.ok(chessBoard);
    }

    /**
     * 请求结束对局(认输)
     *
     * @param userId 请求用户Id
     * @param roomId 房间Id
     * @return 请求结果，是否请求成功
     */
    @GetMapping("/over_request/{userId}/{roomId}")
    public Result overGameRequest(@PathVariable("userId") Long userId, @PathVariable("roomId") Long roomId) {
        // TODO 请求结束对局，并等待对手确认，返回请求结果（是否进入等待状态）

        return null;
    }

    /**
     * 确认结束对局(在双方都停一手或者一方认输的时候调用
     *
     * @param userId 请求用户Id
     * @param roomId 房间Id
     * @return 确认结束对局后，计算对局结果
     */
    @GetMapping("/over_confirm/{userId}/{roomId}")
    public Result overGameConfirm(@PathVariable(value = "userId") Long userId,
                                  @PathVariable(value = "roomId") Long roomId) {
        // TODO 确认结束对局，并计算对局结果，返回给前端
        return null;
    }

    @GetMapping("/result/{userId}/{roomId}")
    public Result getChessBoardResult(@PathVariable("userId") Long userId, @PathVariable("roomId") Long roomId) {
        return Result.ok(chessBoardService.getChessBoardResult(userId, roomId));
    }

    @GetMapping("/stop_once/{userId}/{roomId}")
    public Result stopOnce(@PathVariable("userId") Long userId, @PathVariable("roomId") Long roomId) {
        ChessBoard chessBoard = chessBoardService.stopOnce(userId, roomId);
        return Result.ok(chessBoard);
    }


}
