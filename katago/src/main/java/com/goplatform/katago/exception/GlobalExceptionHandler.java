package com.goplatform.katago.exception;

import com.goplatform.katago.pojo.Constants;
import com.goplatform.katago.pojo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(KataGoException.class)
    @ResponseBody
    public Result kataGoExceptionHandle(HttpServletRequest request, KataGoException e) {
        return Result.error(e.errorCode, e.errorMessage);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result exceptionHandle(HttpServletRequest request, Exception e) {
        logger.error("服务器发生未知异常: " + e.getMessage());
        return Result.error(Constants.RESULT_UNKNOWN_ERROR, e.getMessage());
    }

}
