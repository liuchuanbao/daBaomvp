package com.example.pubinfo.dabaodagger2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.pubinfo.dabaodagger2.data.Poetry;
import com.example.pubinfo.dabaodagger2.di.PoetryQualifier;
import com.google.gson.Gson;

import javax.inject.Inject;

public class AActivity extends AppCompatActivity {


    //添加@Inject注解，表示这个mPoetry是需要注入的
    // 匹配Module中同样注解的方法
    @PoetryQualifier("A")
    @Inject
    Poetry mPoetry;

    // 匹配Module中同样注解的方法
    @PoetryQualifier("B")
    @Inject
    Poetry mPoetryB;

    @Inject
    Gson mGson;

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mTextView = (TextView) findViewById(R.id.tv_main2);
        // 使用Dagger2生成的类 生成组件进行构造，并注入
        MainApplication.getInstance()
                .getAComponent()
                .inject(this);

        String text = mPoetry.getPemo()+",mPoetryA:"+mPoetry+
                mPoetryB.getPemo()+",mPoetryB:"+mPoetryB+
                (mGson == null ? "Gson没被注入" : "Gson已经被注入");
        mTextView.setText(text);
    }
}
