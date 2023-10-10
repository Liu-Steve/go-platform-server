package com.goplatform.server.controller;

import com.goplatform.server.pojo.domain.ChessBoard;
import com.goplatform.server.pojo.domain.ChessDrop;
import org.springframework.web.bind.annotation.*;

/**
 * 棋盘接口
 * 负责：棋盘的配置，下棋的逻辑，悔棋的逻辑，棋盘当前胜负状态逻辑
 */
@RestController
public class ChessBoardController {

    /**
     * 配置棋盘信息，棋盘大小，等待时间等
     * @param userId 房主Id
     * @param roomId 房间Id
     * @param chessBoard 棋盘配置
     * @return 配置结果
     */
    @PostMapping("/{userId}/room/{roomId}/chessBoard")
    public String configChess(@PathVariable(value = "userId") Long userId,
                              @PathVariable(value = "roomId") Long roomId,
                              @RequestBody ChessBoard chessBoard) {
        // TODO 配置棋盘的具体信息，并返回前端具体配置
        return null;
    }

    /**
     * 更改执子颜色
     * @param userId 房主Id
     * @param roomId 房间Id
     * @param color 房主执子颜色
     * @return 更改结果
     */
    @PutMapping ("/{userId}/room/{roomId}/chessBoard")
    public String changeColor(@PathVariable(value = "userId") Long userId,
                              @PathVariable(value = "roomId") Long roomId,
                              @RequestParam(value = "color") Long color) {
        // TODO 更改执子颜色，返回更改结果
        // color：0为白色，1为黑色
        return null;
    }

    /**
     * 查看当前棋盘信息
     * @param userId 房主Id
     * @param roomId 房间Id
     * @return 棋盘信息
     */
    @GetMapping ("/{userId}/room/{roomId}/chessBoard")
    public String getChessBoardInfo(@PathVariable(value = "userId") Long userId,
                              @PathVariable(value = "roomId") Long roomId) {
        // TODO 查询当前棋盘信息，返回查询结果
        return null;
    }

    /**
     * 落子逻辑
     * @param userId 落子用户Id
     * @param roomId 房间Id
     * @param chessDrop 落子信息
     * @return 棋盘信息
     */
    @PostMapping("/{userId}/room/{roomId}/chessBoard/drops")
    public String dropChess(@PathVariable(value = "userId") Long userId,
                            @PathVariable(value = "roomId") Long roomId,
                            @RequestBody ChessDrop chessDrop) {
        // TODO 落子的逻辑，返回棋盘的状态
        /*
         * 1、吃子
         * 2、更新棋盘
         * 3、下满了
         */
        return null;
    }

    /**
     * 请求结束对局
     * @param userId 请求用户Id
     * @param roomId 房间Id
     * @return 请求结果，是否请求成功
     */
    @GetMapping("{userId}/room/{roomId}/chessBoard/overGameRequest")
    public String overGameRequest(@PathVariable(value = "userId") Long userId,
                           @PathVariable(value = "roomId") Long roomId) {
        // TODO 请求结束对局，并等待对手确认，返回请求结果（是否进入等待状态）
        return null;
    }

    /**
     * 确认结束对局
     * @param userId 请求用户Id
     * @param roomId 房间Id
     * @return 确认结束对局后，计算对局结果
     */
    @GetMapping("{userId}/room/{roomId}/chessBoard/overGameConfirm")
    public String overGameConfirm(@PathVariable(value = "userId") Long userId,
                                  @PathVariable(value = "roomId") Long roomId) {
        // TODO 确认结束对局，并计算对局结果，返回给前端
        return null;
    }
}
