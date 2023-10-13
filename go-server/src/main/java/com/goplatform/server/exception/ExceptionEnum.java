package com.goplatform.server.exception;

public enum ExceptionEnum {
    USER_USERNAME_INVALID(10001, "username format is invalid!"),
    USER_PASSWORD_INVALID(10002, "password format is invalid!"),
    USER_EMAIL_INVALID(10003, "email format is invalid!"),
    USER_USERNAME_EXIST(10004, "username is already exist!");
    private int errorCode;
    private String errorMessage;

    ExceptionEnum(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
