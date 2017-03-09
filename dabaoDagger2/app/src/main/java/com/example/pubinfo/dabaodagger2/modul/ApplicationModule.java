package com.example.pubinfo.dabaodagger2.modul;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Module;
import dagger.Provides;

/**
 * 一个Component可以依赖一个或多个Component，并拿到被依赖Component暴露出来的实例，Component的dependencies属性就是确定依赖关系的实现。
 这里的有点像数学里面的交集方式，被依赖的Component主动暴露对象给二者共享，如我们在ApplicationModule提供了一个全局的Gson对象，
 我们想要提供给其他Component时，要在ApplicationComponent显式的提供一个接口:
 */
@Module
public class ApplicationModule {
    /**
     * @Provides 注解表示这个方法是用来创建某个实例对象的，这里我们创建返回Gson对象
     * 方法名随便，一般用provideXXX结构
     * @return 返回注入对象
     */
    @Singleton
    @Provides
    public Gson provideGson(){
        return new Gson();
    }
}

