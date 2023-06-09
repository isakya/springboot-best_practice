package com.izumi.auth;

import com.izumi.modules.sys.enums.UserAdminTypeEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserPerm {
    UserAdminTypeEnum[] value();
}