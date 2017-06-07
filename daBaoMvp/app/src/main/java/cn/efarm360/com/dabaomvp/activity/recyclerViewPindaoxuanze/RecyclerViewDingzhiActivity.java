package cn.efarm360.com.dabaomvp.activity.recyclerViewPindaoxuanze;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cn.efarm360.com.dabaomvp.R;
import cn.efarm360.com.dabaomvp.activity.recyclerViewPindaoxuanze.Channel.ChannelActivity;
import cn.efarm360.com.dabaomvp.activity.recyclerViewPindaoxuanze.drag.DragActivity;

public class RecyclerViewDingzhiActivity extends AppCompatActivity  implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_dingzhi);


        Button mBtnDrag = (Button) findViewById(R.id.btn_drag);
        Button mBtnChannel = (Button) findViewById(R.id.btn_channl);
        mBtnDrag.setOnClickListener(this);
        mBtnChannel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_drag:
                startActivity(new Intent(RecyclerViewDingzhiActivity.this, DragActivity.class));
                break;
            case R.id.btn_channl:
                startActivity(new Intent(RecyclerViewDingzhiActivity.this, ChannelActivity.class));
                break;
        }
    }
}
