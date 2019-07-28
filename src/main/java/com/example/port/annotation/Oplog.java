package com.example.port.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface Oplog {
    String module() default ""; //操作模块

    String describe() default ""; //描述

    String message() default ""; //消息

    String op_Type() default ""; //操作类型

    boolean op_result() default true;//操作结果
}
