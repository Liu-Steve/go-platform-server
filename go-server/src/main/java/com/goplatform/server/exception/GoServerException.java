package com.goplatform.server.exception;

import lombok.Data;

@Data
public class GoServerException extends RuntimeException {
    protected int errorCode;
    protected String errorMessage;

    public GoServerException() {
        super();
    }

    public GoServerException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getErrorMessage());
        this.errorCode = exceptionEnum.getErrorCode();
        this.errorMessage = exceptionEnum.getErrorMessage();
    }

    public GoServerException(int errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public GoServerException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }
}
