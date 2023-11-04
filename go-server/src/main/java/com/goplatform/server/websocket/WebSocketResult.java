package com.goplatform.server.websocket;

import lombok.Data;

@Data
public class WebSocketResult {

    public static final int CHESS_WAIT = 0;
    public static final int CHESS_START = 1;
    public static final int ROOM_ENTER = 6;
    public static final int ROOM_EXIT = 7;

    private int mode;
    private Object message;

    public static WebSocketResult ok(int mode, Object message) {
        WebSocketResult result = new WebSocketResult();
        result.setMode(mode);
        result.setMessage(message);
        return  result;
    }
}
