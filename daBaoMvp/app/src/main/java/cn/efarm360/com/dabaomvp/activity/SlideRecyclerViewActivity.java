package cn.efarm360.com.dabaomvp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import cn.efarm360.com.dabaomvp.R;
import cn.efarm360.com.dabaomvp.adapter.recyclerviewadapter.SlidRecyclerViewAdapter;
import cn.efarm360.com.dabaomvp.utils.ToastUtil;

public class SlideRecyclerViewActivity extends AppCompatActivity implements SlidRecyclerViewAdapter.IonSlidingViewClickListener {

    private RecyclerView mRecyclerView;
    SlidRecyclerViewAdapter mSlidRecyclerViewAdapter;
    ArrayList<String> date ;
    Button btnAll,btnCancle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_recycler_view);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        date = new ArrayList<>();
        initData();
        mSlidRecyclerViewAdapter = new  SlidRecyclerViewAdapter(this,date);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mSlidRecyclerViewAdapter);
       btnAll = (Button) findViewById(R.id.btn_all);
        btnCancle = (Button) findViewById(R.id.btn_cancle);
        btnAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSlidRecyclerViewAdapter.setAllopen(true);
                mSlidRecyclerViewAdapter.notifyDataSetChanged();
            }
        });
        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSlidRecyclerViewAdapter.setAllopen(false);
                mSlidRecyclerViewAdapter.notifyDataSetChanged();
            }
        });
    }

    private void initData() {
        for (int i = 0; i < 20; i++) {
            date.add("测试数据"+i+"条");
        }

    }

    @Override
    public void onItemClick(View view, int position) {
        ToastUtil.showToast(this,"点击事件");
    }

    @Override
    public void onDeleteBtnCilck(View view, int position) {
        ToastUtil.showToast(this,"删除");

        date.remove(position);
        mSlidRecyclerViewAdapter.notifyDataSetChanged();
    }
}
