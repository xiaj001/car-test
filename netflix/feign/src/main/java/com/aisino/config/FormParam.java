package com.aisino.config;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author: xiajun003
 * @Date: 2019/4/3 17:12
 * @Description:
 */
@Target({ElementType.PARAMETER , ElementType.TYPE})
@Retention(RUNTIME)
@Documented
public @interface FormParam {
}
