package com.goplatform.katago.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KataGoException extends RuntimeException {

    protected int errorCode;
    protected String errorMessage;

    public KataGoException() {
        super();
    }

    public KataGoException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getErrorMessage());
        this.errorCode = exceptionEnum.getErrorCode();
        this.errorMessage = exceptionEnum.getErrorMessage();
    }

    public KataGoException(ExceptionEnum exceptionEnum, String errorMessage) {
        super(errorMessage);
        this.errorCode = exceptionEnum.getErrorCode();
        this.errorMessage = errorMessage;
    }

}
