package com.izumi.modules.sys.enums;

/**
 * 用户类型的枚举
 */
public enum UserTypePerm {
    ADMIN(1,"超级管理员"),
    COMMON(2,"普通用户");
    private Integer code;
    private String message;
    UserTypePerm(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
    public Integer getCode() {
        return this.code;
    }
    public String getMessage() {
        return this.message;
    }
}
