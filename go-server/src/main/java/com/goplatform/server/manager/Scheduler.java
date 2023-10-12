package com.goplatform.server.manager;

import com.goplatform.server.pojo.domain.Room;
import lombok.Data;

import javax.annotation.Resource;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Scheduler用于管理房间的创建和存储
 * 是房间的管理者，该类的属性和方法应为静态方法
 */
@Data
public class Scheduler {
    // 用于存储创建的房间，键为房间Id，值为具体房间
    private static ConcurrentHashMap<Long, Room> roomMaps;

}
