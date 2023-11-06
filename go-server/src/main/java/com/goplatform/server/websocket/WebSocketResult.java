package com.goplatform.server.websocket;

import lombok.Data;

@Data
public class WebSocketResult {

    public static final int CHESS_WAIT = 0;
    public static final int CHESS_START = 1;
    public static final int CHESS_STOP_ONCE = 2;
    public static final int CHESS_STOP_ONCE_ANOTHER = 3;
    public static final int CHESS_REQUEST_STOP = 4;
    public static final int ROOM_ENTER = 10;
    public static final int ROOM_EXIT = 11;
    private int mode;
    private Object message;

    public static WebSocketResult ok(int mode, Object message) {
        WebSocketResult result = new WebSocketResult();
        result.setMode(mode);
        result.setMessage(message);
        return  result;
    }
}
