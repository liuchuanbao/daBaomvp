package cn.efarm360.com.dabaomvp.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cn.efarm360.com.dabaomvp.R;
import cn.efarm360.com.dabaomvp.activity.PictureLunBoActivity;
import cn.efarm360.com.dabaomvp.activity.TablayoutActivity;
import cn.efarm360.com.dabaomvp.activity.ToolbarActivity;
import cn.efarm360.com.dabaomvp.adapter.IWifiAdapter;
import cn.efarm360.com.dabaomvp.bean.WifiBean;
import cn.efarm360.com.dabaomvp.present.WifiPresenterImpl;

public class MainActivity extends AppCompatActivity implements IWifiView  {

    IWifiAdapter adapter;
    ListView listView;
     Button btn_add;
    WifiPresenterImpl mWifiPresenterImpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        mWifiPresenterImpl = new WifiPresenterImpl(this);
        adapter = new IWifiAdapter(this);
        mWifiPresenterImpl.onCreate();
    }

    private void initView() {
        listView = (ListView) findViewById(R.id.list_item);
        btn_add= (Button) findViewById(R.id.tv_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(MainActivity.this,DaggerPersionActivity.class));
//                startActivity(new Intent(MainActivity.this,ToolbarActivity.class));
//                startActivity(new Intent(MainActivity.this,TablayoutActivity.class));
                startActivity(new Intent(MainActivity.this,PictureLunBoActivity.class));

            }
        });

    }

    @Override
    public void setListViewData(ArrayList<WifiBean> list) {
        Log.e("xcy", "setListViewData: list = "+ list.size());
        adapter .setMdata(list);
        listView.setAdapter(adapter);
    }

    @Override
    public void showSnackbar(View view) {

    }


}
