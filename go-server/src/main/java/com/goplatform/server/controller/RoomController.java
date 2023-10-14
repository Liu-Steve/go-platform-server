package com.goplatform.server.controller;

import com.goplatform.server.pojo.domain.Result;
import com.goplatform.server.pojo.domain.Room;
import com.goplatform.server.service.RoomService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.jws.soap.SOAPBinding;


/**
 * 房间接口
 * 负责：房间的创建、房间的删除、房间进入
 */
@RestController
@RequestMapping("${apiPrefix}/room")
public class RoomController {

    @Resource
    private RoomService roomService;


    /**
     * 创建房间接口
     * @param userId 房间创建用户
     * @param room 房间基本信息
     * @return 房间具体信息
     */
    @PostMapping("/{userId}")
    public Result createRoom(@PathVariable(value = "userId") Long userId, @RequestBody Room room) {
        // TODO 根据房间配置创建具体房间，安装默认配置创建棋盘，并返回给前端具体房间信息

        //具体的建房逻辑，返回建房结果
        Room roomRes=roomService.createRoom(userId,room);
        return  Result.ok(roomRes);

    }

    /**
     * 进入房间接口
     * @param userId 进入房间用户
     * @param roomId 房间Id
     * @return 进入结果
     */
    @GetMapping("/enter/{userId}/{roomId}")
    public Result enterRoom(@PathVariable(value = "userId") Long userId,
                            @PathVariable(value = "roomId") Long roomId) {
        // TODO 进入房间逻辑，返回给前端进入结果
        Result enterRes=roomService.enterRoom(userId,roomId);

        return enterRes;
    }

    /**
     * 退出房间接口
     * @param userId 退出房间的用户Id
     * @param roomId 房间Id
     * @return 返回退出结果
     */
    @GetMapping("/exit/{userId}/{roomId}")
    public Result exitRoom(@PathVariable(value = "userId") Long userId,
                           @PathVariable(value = "roomId") Long roomId) {
        // TODO 推出房间逻辑，返回给前端进入结果，并将结果保存到历史记录中
        Result exitRes=roomService.exitRoom(userId,roomId);
        return exitRes;
    }
}
