package com.izumi.modules.sys.enums;

import com.izumi.exception.CommonError;

/**
 * 权限模块错误码
 */
public enum AuthErrEnum implements CommonError {
    USERNAME_OR_PASSWORD_ERR(10000001, "用户名或密码错误"),
    TOKEN_EXPIRE(10000002, "token不存在或已过期"),
    NOT_AUTH(10000003, "您没有权限访问，请联系管理员！")
    ;
    private Integer code;
    private String message;

    AuthErrEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
