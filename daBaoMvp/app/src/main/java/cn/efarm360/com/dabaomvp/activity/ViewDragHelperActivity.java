package cn.efarm360.com.dabaomvp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cn.efarm360.com.dabaomvp.R;

public class ViewDragHelperActivity extends AppCompatActivity {

    /**
     * 实现View拖拽的拖拽
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //拖拽控件的简单使用
//        setContentView(R.layout.activity_view_drag_helper);
        //拖拽控件的例子
        setContentView(R.layout.activity_view_drag_helper2);
    }
}
