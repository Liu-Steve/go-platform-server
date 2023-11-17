package com.goplatform.katago.pojo;

import lombok.Data;

import java.util.List;

@Data
public class ChessDrop {
    // 下棋位置 前行后列
    private List<Integer> dropPosition;
}
