package cn.efarm360.com.dabaomvp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import cn.efarm360.com.dabaomvp.R;
import toolbarUtils.StatusBarCompat;

/**
 *  沉浸式Toolbar的使用
 */
public class ToolbarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar);
//        setContentView(R.layout.header_just_username);


        Toolbar toolbar = (Toolbar) findViewById(R.id.id_toolbar);
        setSupportActionBar(toolbar);

        //StatusBarCompat.compat(this, 0xFFFF0000);
        StatusBarCompat.compat(this);
    }
}
