package com.goplatform.server.service;

import com.goplatform.server.pojo.domain.KataCount;
import com.goplatform.server.pojo.domain.Room;


public interface KataService {
    KataCount endCount(int[][] board);

    Room createKataRoom(Long userId);
}
