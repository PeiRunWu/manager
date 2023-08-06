package com.caroLe.manager.common.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.caroLe.manager.common.result.Result;
import com.caroLe.manager.common.type.ErrorType;

import lombok.extern.slf4j.Slf4j;

/**
 * @author CaroLe
 * @Date 2023/4/20 14:41
 * @Description
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandle {

    @ExceptionHandler(BaseException.class)
    public Result<String> baseExceptionHandle(BaseException exception) {
        return Result.failed(exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result<String> exceptionHandle(Exception exception) {
        log.error("Exception 错误:", exception);
        return Result.failed(ErrorType.SERVICE_ERROR);
    }
}