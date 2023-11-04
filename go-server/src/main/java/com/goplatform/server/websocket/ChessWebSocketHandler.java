package com.goplatform.server.websocket;

import com.goplatform.server.exception.ExceptionEnum;
import com.goplatform.server.exception.GoServerException;
import com.goplatform.server.security.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ChessWebSocketHandler extends AbstractWebSocketHandler {

    private static final Logger logger = LoggerFactory.getLogger(ChessWebSocketHandler.class);

    private static final Map<Long, WebSocketSession> sessionMap = new ConcurrentHashMap<>();

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
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        long userId = getUserIdFromSession(session);
        logger.warn("Connection error: " + userId);
        logger.warn(Arrays.toString(exception.getStackTrace()));
        sessionMap.remove(userId);
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
}
