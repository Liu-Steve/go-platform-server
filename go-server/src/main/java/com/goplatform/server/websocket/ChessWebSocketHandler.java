package com.goplatform.server.websocket;

import com.alibaba.fastjson.JSON;
import com.goplatform.server.exception.ExceptionEnum;
import com.goplatform.server.exception.GoServerException;
import com.goplatform.server.manager.Scheduler;
import com.goplatform.server.pojo.domain.Room;
import com.goplatform.server.security.JwtTokenUtil;
import com.goplatform.server.service.RoomService;
import io.jsonwebtoken.Claims;
import org.hibernate.result.UpdateCountOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ChessWebSocketHandler extends AbstractWebSocketHandler {

    private static final Logger logger = LoggerFactory.getLogger(ChessWebSocketHandler.class);

    private static final Map<Long, WebSocketSession> sessionMap = new ConcurrentHashMap<>();

    @Resource
    private Scheduler scheduler;
    @Resource
    private RoomService roomService;
    private long getUserIdFromSession(WebSocketSession session) throws Exception {
        String header = session.getHandshakeHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        String token = JwtTokenUtil.getTokenFromHeader(header);
        Claims claims = JwtTokenUtil.getClaimFromToken(token);
        return JwtTokenUtil.getUserIdFromClaim(claims);
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        long userId = getUserIdFromSession(session);
        logger.debug("User Online: " + userId);
        // 每个用户只能创建一个连接
        WebSocketSession oldSession = sessionMap.get(userId);
        if (oldSession != null) {
            oldSession.close();
        }
        sessionMap.put(userId, session);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        logger.debug("Received message: " + message.getPayload());
        session.sendMessage(new TextMessage("Echo message: " + message.getPayload()));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        long userId = getUserIdFromSession(session);
        logger.debug("User Offline: " + userId);
        sessionMap.remove(userId);
        logger.info("free user if inside room");
        freeUserFromRoom(userId);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        long userId = getUserIdFromSession(session);
        logger.warn("Connection error: " + userId);
        logger.warn(Arrays.toString(exception.getStackTrace()));
        logger.info("free user if inside room");
        freeUserFromRoom(userId);
        sessionMap.remove(userId);
    }

    public static void sendResult(Long userId, Object obj, int mode) {
        WebSocketResult result = WebSocketResult.ok(mode, obj);
        String roomMsg = JSON.toJSONString(result);
        try {
            ChessWebSocketHandler.sendMessage(userId, roomMsg);
        } catch (GoServerException | IOException e) {
            logger.error("can not notice user: {}, websocket not connect", userId);
        }
    }

    /**
     * 校验socket连接是否建立，在需要用户交互操作时调用，后续应该使用AOP切片处理
     */
    public static void checkWebSocketConnection(Long userId) {
        if (!sessionMap.containsKey(userId) || sessionMap.get(userId) == null) {
            throw new GoServerException(ExceptionEnum.CHESS_SOCKET_CONNECTION_NOT_EXIST);
        }
        if (!sessionMap.get(userId).isOpen()) {
            throw new GoServerException(ExceptionEnum.CHESS_SOCKET_CONNECTION_NOT_EXIST);
        }
    }

    /**
     * 对某用户的长连接发送消息
     *
     * @param userId 用户 ID
     * @param message 信息
     * @throws IOException 发送异常，需检查连接状态
     */
    public static void sendMessage(long userId, String message) throws IOException {
        if (!sessionMap.containsKey(userId) || sessionMap.get(userId) == null) {
            throw new GoServerException(ExceptionEnum.CHESS_SOCKET_CONNECTION_NOT_EXIST);
        }
        WebSocketSession session = sessionMap.get(userId);
        session.sendMessage(new TextMessage(message));
    }

    public void freeUserFromRoom(Long userId) {
        // 处理用户-房间映射
        Room room = scheduler.removeUserRoom(userId);
        if (room == null || room.getRoomId() == null) return;
        // 处理房间
        roomService.exitRoom(userId, room.getRoomId());
    }
}
