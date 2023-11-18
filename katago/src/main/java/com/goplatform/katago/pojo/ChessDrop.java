package com.goplatform.katago.pojo;

import com.goplatform.katago.exception.ExceptionEnum;
import com.goplatform.katago.exception.KataGoException;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import static com.goplatform.katago.pojo.Constants.BOARD_SIZE;

@Data
public class ChessDrop {
    // 下棋位置 前行后列
    private List<Integer> dropPosition;

    /**
     * ChessDrop 转 围棋落子字符串
     * 例: [0, 3] -> D1
     *
     * @param drop ChessDrop
     * @return 围棋落子字符串
     */
    public static String dropToString(ChessDrop drop) {
        if (drop == null) {
            throw new KataGoException(ExceptionEnum.CHESS_DROP_ERROR, "drop为空");
        }
        if (drop.getDropPosition().size() != 2) {
            throw new KataGoException(ExceptionEnum.CHESS_DROP_ERROR, drop.toString());
        }
        int row = drop.getDropPosition().get(0);
        int col = drop.getDropPosition().get(1);
        if (row < 0 || row >= BOARD_SIZE || col < 0 || col >= BOARD_SIZE) {
            throw new KataGoException(ExceptionEnum.CHESS_DROP_ERROR, "row: " + row + ", col: " + col);
        }
        char colC = (char) ('A' + col);
        String rowC = String.valueOf(row + 1);
        return colC + rowC;
    }

    /**
     * 围棋落子字符串 转 ChessDrop
     *
     * @param str 围棋落子字符串
     * @return ChessDrop
     */
    public static ChessDrop stringToDrop(String str) {
        if ("pass".equals(str)) { // 停一手返回空
            return null;
        }
        if (str == null || str.length() < 2 || str.length() > 3) {
            throw new KataGoException(ExceptionEnum.CHESS_DROP_ERROR, str);
        }
        try {
            int col = str.charAt(0) - 'A';
            int row = Integer.parseInt(str.substring(1)) - 1;
            ChessDrop drop = new ChessDrop();
            drop.setDropPosition(new ArrayList<>());
            drop.getDropPosition().add(row);
            drop.getDropPosition().add(col);
            return drop;
        } catch (NumberFormatException e) {
            throw new KataGoException(ExceptionEnum.CHESS_DROP_ERROR, e.getMessage());
        }
    }
}
