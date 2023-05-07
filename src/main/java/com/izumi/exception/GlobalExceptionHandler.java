package com.izumi.exception;

import com.izumi.base.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

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
        return CommonResult.fail(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CommonResult methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        log.error("参数校验异常:", e);
        // field1, errMsg1; field2, errMsg2
        String msg = e.getBindingResult().getAllErrors().stream().map(item -> {
            FieldError fieldError = (FieldError) item;
            String field = fieldError.getField();
            String errMsg = fieldError.getDefaultMessage();
            return field + "," + errMsg;
        }).collect(Collectors.joining(";"));
        return CommonResult.fail(9999, msg);
    }
}
