package com.goplatform.server.controller;

import com.goplatform.server.pojo.domain.Result;
import com.goplatform.server.pojo.domain.Room;
import com.goplatform.server.service.RoomService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.jws.soap.SOAPBinding;
import java.rmi.server.RemoteRef;


/**
 * 房间接口
 * 负责：房间的创建、房间的删除、房间进入
 * 创建房间和退出房间都应该建立websocket连接
 */
@RestController
@RequestMapping("${apiPrefix}/room")
public class RoomController {

    @Resource
    private RoomService roomService;


    /**
     * 创建房间接口
     *
     * @param userId 房间创建用户
     * @return 房间具体信息
     */
    @PutMapping("/{userId}")
    public Result createRoom(@PathVariable("userId") Long userId) {
        // 根据房间配置创建具体房间，安装默认配置创建棋盘，并返回给前端具体房间信息

        //具体的建房逻辑，返回建房结果
        Room roomRes = roomService.createRoom(userId);
        return Result.ok(roomRes);

    }

    /**
     *
     * 创建人机对战房间
     * @param userId 房间创建用户
     * @return 人机对战信息
     */
    @PutMapping("/kata/{userId}")
    public Result createKataRoom(@PathVariable Long userId) {
        Room kataRoom = roomService.createKataRoom(userId);
        return Result.ok(kataRoom);
    }

    /**
     * 进入房间接口
     *
     * @param userId 进入房间用户
     * @param roomId 房间Id
     * @return 进入结果
     */
    @GetMapping("/enter/{userId}/{roomId}")
    public Result enterRoom(@PathVariable("userId") Long userId,
                            @PathVariable("roomId") Long roomId) {
        // TODO 进入房间逻辑，返回给前端进入结果
        Room room = roomService.enterRoom(userId, roomId);

        return Result.ok(room);
    }

    /**
     * 退出房间接口
     *
     * @param userId 退出房间的用户Id
     * @param roomId 房间Id
     * @return 返回退出结果
     */
    @GetMapping("/exit/{userId}/{roomId}")
    public Result exitRoom(@PathVariable("userId") Long userId,
                           @PathVariable("roomId") Long roomId) {
        // TODO 推出房间逻辑，返回给前端进入结果，并将结果保存到历史记录中
        Room room = roomService.exitRoom(userId, roomId);
        return Result.ok(room);
    }

    /**
     * 查询所有房间信息
     *
     * @return 所有房间信息
     */
    @GetMapping("/list")
    public Result listRoom() {
        return Result.ok(roomService.listRoom());
    }

    @GetMapping("/{roomId}")
    public Result findRoomById(@PathVariable("roomId") Long roomId) {
        return Result.ok(roomService.findRoomById(roomId));
    }


}
