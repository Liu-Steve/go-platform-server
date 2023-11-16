package com.goplatform.server.controller;

import com.goplatform.server.pojo.domain.Result;
import com.goplatform.server.pojo.domain.Room;
import com.goplatform.server.service.KataService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/kata")
public class KataController {
    @Resource
    private KataService kataService;
    @PutMapping("/room/{userId}")
    public Result createKataRoom(@PathVariable Long userId) {
        Room kataRoom = kataService.createKataRoom(userId);
        return Result.ok(kataRoom);
    }


}
