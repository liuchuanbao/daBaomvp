package com.example.pubinfo.dabaodagger2.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * 调用同一个MainComponent实例多次注入的时候每次都重新生成Poetry实例，
 * 有时候我们需要只希望生成一个共用实例的时候应该怎么办呢，
 * 这里我们就需要用到Dagger2的@Scope属性了，Scope是作用域的意思，我们先自定义一个@Scope注解:
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PoetryScope {
}
