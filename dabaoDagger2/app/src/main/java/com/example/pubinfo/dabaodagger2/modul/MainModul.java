package com.example.pubinfo.dabaodagger2.modul;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @Modul 注解表示这个类提供生成一些实例用于注入  第三方jar包对象的注入
 *
 */

@Module
public class MainModul {
    @Provides
    public Gson provideGson(){
        return new Gson();
    }
}
