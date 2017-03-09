package cn.efarm360.com.dabaomvp.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import javax.inject.Inject;

import cn.efarm360.com.dabaomvp.R;
import cn.efarm360.com.dabaomvp.bean.Persion;
import cn.efarm360.com.dabaomvp.component.AppComponent;
import cn.efarm360.com.dabaomvp.component.DaggerActivityComponent;
import cn.efarm360.com.dabaomvp.component.DaggerAppComponent;
import cn.efarm360.com.dabaomvp.component.DaggerPersionComponent;
import cn.efarm360.com.dabaomvp.component.PersionComponent;
import cn.efarm360.com.dabaomvp.moudle.ActivityMoudule;
import cn.efarm360.com.dabaomvp.moudle.AppModule;
import cn.efarm360.com.dabaomvp.moudle.PersionModule;


public class DaggerPersionActivity extends AppCompatActivity {

                     @Inject   //标明需要注入的对象  这个对象是代替了new  对象名（）；
                     Persion persion;
               TextView tvPersion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger_persion);
        // 构造桥梁对象
       PersionComponent component = DaggerPersionComponent.builder().persionModule(new PersionModule(this)).build();
        //注入
        component.inject(this);
//        persion.setName("大宝");
//        persion.setAge(18);
//        tvPersion = (TextView)findViewById(R.id.tv_persin);
//
//        tvPersion.setText(persion.getName());

        // 依赖对象　Component
        AppComponent appCom = DaggerAppComponent.builder().appModule(new AppModule(this)).build();

        // 子类依赖对象 ，并注入
        DaggerActivityComponent.builder()
                .appComponent(appCom)
                .activityMoudule(new ActivityMoudule())
                .build()
                .inject(DaggerPersionActivity.this);

    }
}
