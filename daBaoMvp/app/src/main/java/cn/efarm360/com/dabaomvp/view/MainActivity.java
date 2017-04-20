package cn.efarm360.com.dabaomvp.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerViewAccessibilityDelegate;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.efarm360.com.dabaomvp.R;
import cn.efarm360.com.dabaomvp.ZiDingYiview.TextViewClickableSpan;
import cn.efarm360.com.dabaomvp.ZiDingYiview.VDHLinearLayout2;
import cn.efarm360.com.dabaomvp.activity.MultiItemRvActivityActivity;
import cn.efarm360.com.dabaomvp.activity.PictureLunBoActivity;
import cn.efarm360.com.dabaomvp.activity.RecycleViewAdapterActivity;
import cn.efarm360.com.dabaomvp.activity.RecyclerActivity;
import cn.efarm360.com.dabaomvp.activity.SegmentGroupActivity;
import cn.efarm360.com.dabaomvp.activity.TablayoutActivity;
import cn.efarm360.com.dabaomvp.activity.ToolbarActivity;
import cn.efarm360.com.dabaomvp.activity.UpViewActivity;
import cn.efarm360.com.dabaomvp.activity.ViewDragHelperActivity;
import cn.efarm360.com.dabaomvp.adapter.IWifiAdapter;
import cn.efarm360.com.dabaomvp.bean.WifiBean;
import cn.efarm360.com.dabaomvp.present.WifiPresenterImpl;

public class MainActivity extends AppCompatActivity implements IWifiView ,TextViewClickableSpan.OnTextViewClickListener {

    IWifiAdapter adapter;
    ListView listView;
     Button btn_add;
    WifiPresenterImpl mWifiPresenterImpl;
   TextView tv_clikAble;
 //可以点击的textveew
    TextViewClickableSpan textViewClickableSpan;

    private  String text ="我是可以点击变色的textview";
    String stre = "点击跳转";
    String s = "...";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewClickableSpan = new TextViewClickableSpan(stre,R.color.red,this);
        initView();
        Toolbar toolbar = (Toolbar) findViewById(R.id.id_toolbar);
        setSupportActionBar(toolbar);

        mWifiPresenterImpl = new WifiPresenterImpl(this);
        adapter = new IWifiAdapter(this);
        mWifiPresenterImpl.onCreate();
//

         SpannableString spantt=new SpannableString(text);
        tv_clikAble.setMovementMethod(LinkMovementMethod.getInstance());
        spantt.setSpan(textViewClickableSpan,4,6, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tv_clikAble.setText(spantt);

    }

    private void initView() {
        listView = (ListView) findViewById(R.id.list_item);
        btn_add= (Button) findViewById(R.id.tv_add);
        tv_clikAble = (TextView) findViewById(R.id.tv_clikAble);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Dagger2开源库的使用
//                startActivity(new Intent(MainActivity.this,DaggerPersionActivity.class));
                // 沉浸式标题栏
//                startActivity(new Intent(MainActivity.this,ToolbarActivity.class));
                // Tablayout 的使用
//                startActivity(new Intent(MainActivity.this,TablayoutActivity.class));
                // 图片轮播
//                startActivity(new Intent(MainActivity.this,PictureLunBoActivity.class));
                //仿qq标题栏切换
//                startActivity(new Intent(MainActivity.this,SegmentGroupActivity.class));
                //view实现拖拽功能
//                startActivity(new Intent(MainActivity.this,ViewDragHelperActivity.class));
                //实现上推换界面
//                startActivity(new Intent(MainActivity.this,UpViewActivity.class));
                //实现上万能Aadpter 的使用 单个
//                startActivity(new Intent(MainActivity.this,RecycleViewAdapterActivity.class));
//                //实现上万能Aadpter 的使用 多个
//                startActivity(new Intent(MainActivity.this,MultiItemRvActivityActivity.class));
//                 实现recycle 的左划删除
                startActivity(new Intent(MainActivity.this,RecyclerActivity.class));

            }
        });

    }

    /**
     * 计时器
     * 调用 ：  timer.start();
     *
     */

    private CountDownTimer timer = new CountDownTimer(60000, 1000) {

        @Override
        public void onTick(long millisUntilFinished) {
//            btnGET.setText((millisUntilFinished / 1000) + "秒后可重发");
//            btnGET.setTextColor(Color.parseColor("#ffffff"));
        }

        @Override
        public void onFinish() {
//            btnGET.setEnabled(true);
//            btnGET.setClickable(true);
//            btnGET.setText("获取验证码");
//            btnGET.setTextColor(Color.parseColor("#000000"));
        }
    };

    @Override
    public void setListViewData(ArrayList<WifiBean> list) {
        Log.e("xcy", "setListViewData: list = "+ list.size());
        adapter .setMdata(list);
        listView.setAdapter(adapter);
    }

    @Override
    public void showSnackbar(View view) {

    }


    @Override
    public void onTextViewClick(View view) {
        //view实现拖拽功能
                startActivity(new Intent(MainActivity.this,ViewDragHelperActivity.class));
    }
}
