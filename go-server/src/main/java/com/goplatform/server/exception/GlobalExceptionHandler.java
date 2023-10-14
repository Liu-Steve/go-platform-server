package com.goplatform.server.exception;

import com.goplatform.server.pojo.constant.Constants;
import com.goplatform.server.pojo.domain.Result;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 拦截业务异常
     * @param req 请求
     * @param e GoServerException异常
     * @return 返回异常信息
     */
    @ExceptionHandler(GoServerException.class)
    @ResponseBody
    public Result GoServerExceptionHandle(HttpServletRequest req, GoServerException e) {
        return Result.error(e.getErrorCode(), e.getErrorMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    public Result GoServerExceptionHandle(HttpServletRequest req, AccessDeniedException e) {
        return Result.error(Constants.RESULT_ACCESS_DENY, "鉴权失败，不允许访问！");
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result GoServerExceptionHandle(HttpServletRequest req, Exception e) {
        return Result.error(Constants.RESULT_UNKNOWN_ERROR, "服务器发生未知异常！");
    }
}
