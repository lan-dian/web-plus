package com.landao.web.plus.annotation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 支持别名的依赖注入
 */
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Autowired
@Qualifier
public @interface DI {

    @AliasFor(annotation = Autowired.class)
    boolean required() default true;

    @AliasFor(annotation = Qualifier.class)
    String value() default "";

}
