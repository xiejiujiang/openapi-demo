package com.chanjet.changsha.bank.pay.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @program: webflux
 * @description:
 * @author: nxw
 * @create: 2019-07-10 15:31
 **/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NeedToken {
    boolean needToken() default true;
}
