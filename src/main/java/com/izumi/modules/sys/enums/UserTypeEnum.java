package com.izumi.modules.sys.enums;

import java.util.Arrays;

/**
 * 用户类型的枚举
 */
public enum UserTypeEnum {
    ADMIN(1,"超级管理员"),
    COMMON(2,"普通用户");
    private Integer code;
    private String message;
    UserTypeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 由 code 转 enum
     * @param code
     * @return
     */
    public static UserTypeEnum codeToEnum(Integer code) {
        return Arrays.stream(UserTypeEnum.values()).filter(item -> {
            return item.code.equals(code);
        }).findFirst().orElse(UserTypeEnum.COMMON);
    }
    public Integer getCode() {
        return this.code;
    }
    public String getMessage() {
        return this.message;
    }
}
