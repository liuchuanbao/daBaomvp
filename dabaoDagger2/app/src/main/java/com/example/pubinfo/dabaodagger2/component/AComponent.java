package com.example.pubinfo.dabaodagger2.component;

import com.example.pubinfo.dabaodagger2.di.AScope;
import com.example.pubinfo.dabaodagger2.modul.AModule;
import com.example.pubinfo.dabaodagger2.AActivity;

import dagger.Subcomponent;

/**
 * 如果我们需要父组件全部的提供对象，这时我们可以用包含方式而不是用依赖方式，相比于依赖方式，
 * 包含方式不需要父组件显式显露对象，就可以拿到父组件全部对象。
 * 且SubComponent只需要在父Component接口中声明就可以了。添加多一个AActivity,AComponent:
 */

@AScope
@Subcomponent(modules = AModule.class)
public interface AComponent {
      void inject(AActivity activity);
}
