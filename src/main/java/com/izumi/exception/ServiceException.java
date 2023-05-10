package com.izumi.exception;

import lombok.Data;

/**
 * 自定义业务异常
 */
@Data
public class ServiceException extends RuntimeException {
    private int code;
    private String message;

    private ServiceException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /***
     * 抛出自定义异常
     * @param code
     * @param message
     */
    public static void throwBiz(int code, String message) {
        throw new ServiceException(code, message);
    }

    /***
     * 抛出自定义异常-错误码的方式
     * @param commonError
     */
    public static void throwBiz(CommonError commonError) {
        throw new ServiceException(commonError.getCode(), commonError.getMessage());
    }
}
