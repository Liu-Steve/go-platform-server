package com.goplatform.katago.service;

import com.goplatform.katago.exception.ExceptionEnum;
import com.goplatform.katago.exception.KataGoException;
import com.goplatform.katago.kata.KataAgent;
import com.goplatform.katago.pojo.ChessDrop;
import com.goplatform.katago.pojo.KataCount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class KataAgentService {

    private static final Map<Long, KataAgent> roomAgents = new ConcurrentHashMap<>();

    private static final Logger logger = LoggerFactory.getLogger(KataAgentService.class);

    public void start(long roomId) {
        try {
            KataAgent agent = new KataAgent();
            agent.init();
            roomAgents.put(roomId, agent);
        } catch (KataGoException e) {
            roomAgents.remove(roomId); // init 失败了则不添加
            throw e;
        }
    }

    public void destroy(long roomId) {
        try {
            KataAgent agent = roomAgents.get(roomId);
            if (agent != null) {
                agent.quit();
            }
            roomAgents.remove(roomId);
        } catch (KataGoException e) {
            roomAgents.remove(roomId); // 即使 quit 失败了也需要移除
            throw e;
        }
    }

    public void play(long roomId, String color, ChessDrop drop) {
        KataAgent agent = roomAgents.get(roomId);
        if (agent == null) {
            throw new KataGoException(ExceptionEnum.ROOM_ERROR, String.valueOf(roomId));
        }
        agent.play(color, drop);
    }

    public ChessDrop gen(long roomId, String color) {
        KataAgent agent = roomAgents.get(roomId);
        if (agent == null) {
            throw new KataGoException(ExceptionEnum.ROOM_ERROR, String.valueOf(roomId));
        }
        return agent.gen(color);
    }

    public KataCount count(long roomId) {
        KataAgent agent = roomAgents.get(roomId);
        if (agent == null) {
            throw new KataGoException(ExceptionEnum.ROOM_ERROR, String.valueOf(roomId));
        }
        return agent.count();
    }

}
