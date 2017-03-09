package com.example.pubinfo.dabaodagger2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.pubinfo.dabaodagger2.component.MainComponent;
import com.example.pubinfo.dabaodagger2.data.Poetry;
import com.google.gson.Gson;

import javax.inject.Inject;

public class Main2Activity extends AppCompatActivity {


    //添加@Inject注解，表示这个mPoetry是需要注入的
    @Inject
    Poetry mPoetry;

    @Inject
    Gson mGson;

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mTextView = (TextView) findViewById(R.id.tv_main2);
        // 使用Dagger2生成的类 生成组件进行构造，并注入
        MainComponent.getInstance()
                .inject(this);

        String json = mGson.toJson(mPoetry);
        String text = json + ",mPoetry:"+mPoetry;
        mTextView.setText(text);
    }
}
