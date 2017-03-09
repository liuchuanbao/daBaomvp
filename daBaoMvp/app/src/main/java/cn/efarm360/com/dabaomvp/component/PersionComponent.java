package cn.efarm360.com.dabaomvp.component;

import javax.inject.Singleton;

import cn.efarm360.com.dabaomvp.moudle.PersionModule;
import cn.efarm360.com.dabaomvp.view.DaggerPersionActivity;
import dagger.Component;

/**
 * Created by pubinfo on 2017/3/1.
 */
@Singleton
@Component(modules = PersionModule.class)  // 作为桥梁，沟通调用者和依赖对象库
public interface PersionComponent {
    //定义注入的方法
    void inject(DaggerPersionActivity activity);
}
