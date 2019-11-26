package com.springmall.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ControllerLog {
    /**
     * 用户操作类型
     */
    ActionType actionType();

    /**
     * 操作描述
     */
    String description() default "";
}
