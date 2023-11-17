package com.goplatform.server.pojo.domain;

import lombok.Data;

import java.util.List;

/**
 * 落子时前端向后端传入数据的包装
 */
@Data
public class ChessDrop {
    // 下棋位置，前行后列
    private List<Integer> dropPosition;
}
