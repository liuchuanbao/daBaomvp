package com.example.pubinfo.dabaodagger2.data;


import javax.inject.Inject;

/**
 * Created by pubinfo on 2017/3/7.
 */

public class Poetry {
    private String mPemo;

    // 用Inject标记构造函数,表示用它来注入到目标对象中去
//    @Inject
//    public Poetry() {
////        mPemo = "生活就像海洋";
//    }

    /**
     * @Module的优先级高于@Inject。
     *
     * 我们提供了两个可以生成Poetry实例的方法，
     * 一个是在Poetry类的构造函数时候用@Inject提供的实例创建方法，
     * 一个是在PoetryModule中的@Privodes注解的providePoetry方法
     * @param name
     */
    public Poetry(String name) {
//        mPemo = "生活就像海洋";
        mPemo =name;
    }

    public String getPemo() {
        return mPemo;
    }
}
