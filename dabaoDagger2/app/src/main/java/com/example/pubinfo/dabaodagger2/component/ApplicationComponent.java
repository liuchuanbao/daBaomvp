package com.example.pubinfo.dabaodagger2.component;

import com.example.pubinfo.dabaodagger2.modul.AModule;
import com.example.pubinfo.dabaodagger2.modul.ApplicationModule;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by pubinfo on 2017/3/7.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    Gson getGson();// 暴露Gson对象接口


    //AComponent plus();
    AComponent plus(AModule module);//添加声明 SubComponent只需要在父Component接口中声明就可以了
}