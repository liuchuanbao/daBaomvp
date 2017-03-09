package com.example.pubinfo.dabaodagger2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.pubinfo.dabaodagger2.component.MainComponent;
import com.example.pubinfo.dabaodagger2.data.Poetry;
import com.google.gson.Gson;

import javax.inject.Inject;

/**
 * 顺序执行：

 从Module中查找类实例创建方法
 Module中存在创建方法，则看此创建方法有没有参数
 如果有参数，这些参数也是由Component提供的，返回步骤1逐一生成参数类实例，最后再生成最终类实例
 如果无参数，则直接由这个方法生成最终类实例

 Module中没有创建方法，则从构造函数里面找那个用@Inject注解的构造函数
 如果该构造函数有参数，则也是返回到步骤1逐一生成参数类实例，最后调用该构造函数生成类实例
 如果该构造函数无参数，则直接调用该构造函数生成类实例
 */
public class MainActivity extends AppCompatActivity {

    // 添加@Inject注解，表示这个mPoetry需要注入的
    @Inject
    Poetry mPoetry;

    @Inject
    Gson mGson;

    private TextView tv1,tv2,tv3,tv4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

          //使用Dagger2生成的类，生成组件进行构造，并注入
//        // 使用Dagger2生成的类 生成组件进行构造，并注入
//        DaggerMainComponent.builder()  interface时候的注入
//                .build()
//                .inject(this);
        // 使用Dagger2生成的类 生成组件进行构造，并注入
        MainComponent.getInstance()
                .inject(this);

        tv1.setText(mPoetry.getPemo());
        String json = mGson.toJson(mPoetry);
        tv2.setText(json);
        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Main2Activity.class));
            }
        });
        tv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,AActivity.class));
            }
        });
    }

    private void initView() {
        tv1 = (TextView) findViewById(R.id.tv_1);
        tv2 = (TextView) findViewById(R.id.tv_2);
        tv3 = (TextView) findViewById(R.id.tv_3);
        tv4 = (TextView) findViewById(R.id.tv_4);
    }
}
