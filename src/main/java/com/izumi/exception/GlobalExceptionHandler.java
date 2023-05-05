package com.izumi.exception;

import com.izumi.base.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 统一异常处理
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public CommonResult handler(Exception e) {
        log.error("异常", e);
        return CommonResult.fail(e.getMessage());
    }

    @ExceptionHandler(ServiceException.class)
    public CommonResult serviceExceptionHandler(ServiceException e) {
        log.error("业务异常:", e);
        return CommonResult.fail(9910, e.getMessage());
    }
}
