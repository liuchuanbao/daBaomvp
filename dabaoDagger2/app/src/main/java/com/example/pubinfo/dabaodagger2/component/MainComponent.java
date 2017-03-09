package com.example.pubinfo.dabaodagger2.component;

import com.example.pubinfo.dabaodagger2.Main2Activity;
import com.example.pubinfo.dabaodagger2.MainActivity;
import com.example.pubinfo.dabaodagger2.MainApplication;
import com.example.pubinfo.dabaodagger2.di.PoetryScope;
import com.example.pubinfo.dabaodagger2.modul.MainModul;
import com.example.pubinfo.dabaodagger2.modul.PoetryModul;

import dagger.Component;

/**
 *  用@conponent表示这个接口是一个连接器，能用@Comonent注解的只能是interface或者抽象类
 *
 *  @Component(modules = MainModul.class)
 *这里表示Component会从MainModule类中拿那些用@Provides注解的方法来生成需要注入的实例
 *
 *
 * @Component(modules = {MainModul.class,PoetryModul.class})
 * //这里表示Component会从MainModule类中拿那些用@Provides注解的方法来生成需要注入的实例
 *
 * @PoetryScope  同时在Module与Component加上这个自定义Scope:
 *
 * dependencies  /Component的dependencies属性就是确定依赖关系的实现。
 自定义注解的使用*/
@PoetryScope
@Component(dependencies = ApplicationComponent.class,modules = PoetryModul.class)
public abstract class MainComponent{
//public interface MainComponent {
    /**
     * 需要用到这个连接器的对象，就是这个对象里面需要注入的属性，（被标记为@inject的属性）
     * 这里的inject表示注入的意思，这个方法名可以随意更改 （但是建议使用injectXXX）
     */
    /**
     * ，调用同一个MainComponent实例多次注入的时候每次都重新生成Poetry实例，
     * 有时候我们需要只希望生成一个共用实例的时候应该怎么办呢，这里我们就需要用到Dagger2的@Scope属性了，
     * Scope是作用域的意思，我们先自定义一个@Scope注解:
     * @param activity
     */
    public abstract void inject(MainActivity activity);
    public abstract void inject(Main2Activity activity);

    private static MainComponent sComponent;
    public static MainComponent getInstance(){
        if (sComponent == null){
            sComponent = DaggerMainComponent.builder()
                    .applicationComponent(MainApplication.getInstance()
                            .getApplicationComponent())
                    .build();
        }
        return sComponent;
    }
}
