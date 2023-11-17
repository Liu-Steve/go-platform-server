package com.goplatform.server.service;

import com.goplatform.server.pojo.domain.ChessDrop;
import com.goplatform.server.pojo.domain.KataCount;


public interface KataService {
    // GET /count/roomId
    KataCount endCount(Long roomId);

    // PUT /start/roomId
    void start(Long roomId);
    // POST /play/roomId/{color} 用户的颜色
    void play(Long roomId, ChessDrop chessDrop, String color);
    // GET /gen/roomId/{color} null代表下不了，认输  AI的颜色
    ChessDrop gen(Long roomId, String color);
    // DELETE /destroy/roomId
    void destroy(Long roomId);

}
