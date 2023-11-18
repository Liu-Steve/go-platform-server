package com.goplatform.katago.pojo;

import com.goplatform.katago.exception.ExceptionEnum;
import com.goplatform.katago.exception.KataGoException;
import lombok.AllArgsConstructor;
import lombok.Data;

import static com.goplatform.katago.pojo.Constants.BOARD_SIZE;

@Data
public class KataCount {

    private double black;

    private double white;

    public static KataCount stringToCount(String str) {
        double half = BOARD_SIZE * BOARD_SIZE / 2.0;
        KataCount count = new KataCount();
        if ("0".equals(str)) {
            count.black = half;
            count.white = half;
            return count;
        }
        if (str == null || str.length() < 3) {
            throw new KataGoException(ExceptionEnum.KATA_COUNT_ERROR, str);
        }
        try {
            char color = str.charAt(0);
            double bias = Double.parseDouble(str.substring(2));

            if (color == 'W') {
                bias = -bias;
            } else if (color != 'B') {
                throw new KataGoException(ExceptionEnum.KATA_COUNT_ERROR, "未知的颜色" + color);
            }

            bias /= 2.0;
            count.black = half + bias;
            count.white = half - bias;
            return count;

        } catch (NullPointerException | NumberFormatException e) {
            throw new KataGoException(ExceptionEnum.KATA_COUNT_ERROR, str);
        }
    }

}
