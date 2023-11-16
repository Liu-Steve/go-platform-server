package com.goplatform.server.service;

import com.goplatform.server.pojo.domain.KataCount;


public interface KataService {
    KataCount endCount(int[][] board);
}
