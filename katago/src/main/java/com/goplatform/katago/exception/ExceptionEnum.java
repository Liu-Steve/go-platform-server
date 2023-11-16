package com.goplatform.katago.exception;

import lombok.Getter;

@Getter
public enum ExceptionEnum {

    KATA_EXE_FAIL(20001, "KataGo可执行程序出错"),
    CHESSBOARD_ERROR(21001, "棋盘解析错误");

    private final int errorCode;
    private final String errorMessage;

    ExceptionEnum(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

}
