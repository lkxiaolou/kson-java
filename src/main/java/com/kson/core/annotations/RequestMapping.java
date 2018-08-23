package com.kson.core.annotations;

import com.kson.core.enums.RequestMethodEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapping {

    String[] path() default {};

    RequestMethodEnum[] method() default {};

}
