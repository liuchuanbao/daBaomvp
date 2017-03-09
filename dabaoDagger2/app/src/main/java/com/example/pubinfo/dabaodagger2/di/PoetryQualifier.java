package com.example.pubinfo.dabaodagger2.di;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 *定义@Qualifier限定符来匹配注入方法了，添加一个自定义Qualifier并修AMoudule，AActivity:
 */

@Qualifier
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface PoetryQualifier {
    String value() default "";
}