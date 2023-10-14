package com.goplatform.server.pojo.domain;

import com.goplatform.server.exception.ExceptionEnum;
import com.goplatform.server.pojo.constant.Constants;
import lombok.Data;

@Data
public class Result {
    private int resultCode;
    private String resultMessage;
    private Object result;

    public static Result ok(String resultMessage, Object obj) {
        Result result = new Result();
        result.setResultCode(Constants.RESULT_OK);
        result.setResultMessage(resultMessage);
        if (obj != null) {
            result.setResult(obj);
        }
        return  result;
    }

    public static Result ok(String resultMessage) {
        return Result.ok(resultMessage, null);
    }

    public static Result ok(Object obj) {
        return Result.ok("OK", obj);
    }

    public static Result error(int resultCode, String resultMessage) {
        Result result = new Result();
        result.setResultCode(resultCode);
        result.setResultMessage(resultMessage);
        return result;
    }

    public static Result error(String resultMessage) {
        return Result.error(Constants.RESULT_UNKNOWN_ERROR, resultMessage);
    }

    public static Result error(ExceptionEnum exceptionEnum) {
        return Result.error(exceptionEnum.getErrorCode(), exceptionEnum.getErrorMessage());
    }
}
