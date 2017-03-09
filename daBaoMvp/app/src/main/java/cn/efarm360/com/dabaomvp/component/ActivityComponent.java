package cn.efarm360.com.dabaomvp.component;

import cn.efarm360.com.dabaomvp.moudle.ActivityMoudule;
import cn.efarm360.com.dabaomvp.view.DaggerPersionActivity;
import dagger.Component;

/**
 *   子的Component
 */
@Component(dependencies = AppComponent.class,modules = ActivityMoudule.class)
public interface ActivityComponent {
    // 注入
    void inject(DaggerPersionActivity activity);
}
