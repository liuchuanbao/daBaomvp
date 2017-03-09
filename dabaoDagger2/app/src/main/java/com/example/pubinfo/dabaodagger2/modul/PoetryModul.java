package com.example.pubinfo.dabaodagger2.modul;

import com.example.pubinfo.dabaodagger2.data.Poetry;
import com.example.pubinfo.dabaodagger2.di.PoetryScope;

import dagger.Module;
import dagger.Provides;

/**
 * Component可以依赖多个Module对象，以上的构造方法与生成方法都是无参生成实例的，
 * 如果我们带参数应该怎么做了？我们创建多一个PoetryModule用于提供Poetry实例:
 *
 *
 */

@Module
public class PoetryModul {

    //这个方法需要传入一个String参数，在Dagger2注入中，这些参数也是注入形式的也就是
    //要有其他对方提供参数的poems的生成，不然会出错
    // @PoetryScope  自定义注解 保证单例
    @PoetryScope
    @Provides
    public Poetry providePoetry(String name){
         return new Poetry(name);
    }
  // 这里提供一个生成String的方法，在这个Modul里生成Poetry对象时，会查到这里 /
    // 可以为上面提供String类型的参数

    /**
     * 所以得参数都需要自己来定义方法获取
     * @return
     */
  @Provides
    public String providePoems(){
       return "只有意志坚强的人,才能到达彼岸";
  }
}
