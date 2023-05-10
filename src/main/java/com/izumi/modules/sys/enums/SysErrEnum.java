package com.izumi.modules.sys.enums;

import com.izumi.exception.CommonError;

/**
 * 系统管理模块错误码
 */
public enum SysErrEnum implements CommonError {
    NOT_DATA(10010001, "数据不存在")
    ;
    private Integer code;
    private String message;

    SysErrEnum(Integer code, String message) {
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
