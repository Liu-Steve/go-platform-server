package com.goplatform.katago.pojo;

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
        return result;
    }

    public static Result error(int resultCode, String resultMessage) {
        Result result = new Result();
        result.setResultCode(resultCode);
        result.setResultMessage(resultMessage);
        return result;
    }

}
