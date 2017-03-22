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
//        setContentView(R.layout.activity_toolbar);
        setContentView(R.layout.activity_toolbar_zidingyi);


        Toolbar toolbar = (Toolbar) findViewById(R.id.id_toolbar);
//        setSupportActionBar(toolbar);
//
//        //StatusBarCompat.compat(this, 0xFFFF0000);
//        StatusBarCompat.compat(this);

//        toolbar.setLogo(R.drawable.ic_launcher);
//// Title
//        toolbar.setTitle("My Title");
//// Sub Title
//        toolbar.setSubtitle("Sub title");
        setSupportActionBar(toolbar);

// Navigation Icon 要設定在 setSupoortActionBar 才有作用
// 否則會出現 back button
//        toolbar.setNavigationIcon(R.drawable.banner2);
    }
}
