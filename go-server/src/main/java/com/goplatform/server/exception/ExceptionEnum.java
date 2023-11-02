package com.goplatform.server.exception;

public enum ExceptionEnum {
    USER_REGISTER_USERNAME_INVALID(10001, "用户名格式不正确！"),
    USER_REGISTER_PASSWORD_INVALID(10002, "密码格式不正确！"),
    USER_REGISTER_EMAIL_INVALID(10003, "邮箱格数不正确！"),
    USER_REGISTER_USERNAME_EXIST(10004, "用户名已经存在！"),
    USER_LONGIN_FAILED(10005, "登陆失败，用户名、邮箱或密码错误！"),
    USER_USERID_INVALID(10006, "用户id无效！"),
    USER_USER_STATUS_INVALID(10007, "用户状态错误！无法进行该操作"),
    ROOM_ID_INVALID(11001, "房间id: %s无效！"),
    ROOM_USER_NOT_INSIDE(11002, "用户：%s 不在房间%s 中！");
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
