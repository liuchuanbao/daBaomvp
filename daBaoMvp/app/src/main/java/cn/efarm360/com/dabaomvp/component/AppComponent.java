package cn.efarm360.com.dabaomvp.component;

import android.content.Context;

import cn.efarm360.com.dabaomvp.moudle.AppModule;
import dagger.Component;

/**
 * Created by pubinfo on 2017/3/1.
 * 为Module中需要向下层提供Context对象，而其与下层的联系时通过Component
 ，所以需要在这里声明一个其所提供对象的方法。以便下层Module获取。
 */
@Component(modules = AppModule.class)
public interface AppComponent {
    // 向其下层提供Context 对象
    Context proContext();
}
