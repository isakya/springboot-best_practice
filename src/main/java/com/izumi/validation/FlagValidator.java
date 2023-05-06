package com.izumi.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;
/**
 * flag 注解
 * @author mldong
 *
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER,ElementType.FIELD})
@Constraint(validatedBy = FlagValidatorClass.class)
public @interface FlagValidator  {
    // flag的有效值多个使用,隔开
    String values();
    // 是否必填
    boolean required() default false;
    // 提示内容
    String message() default "flag不存在";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
