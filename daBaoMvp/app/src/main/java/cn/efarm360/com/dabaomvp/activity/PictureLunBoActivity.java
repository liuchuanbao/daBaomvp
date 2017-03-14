package cn.efarm360.com.dabaomvp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.Arrays;

import cn.bingoogolapple.bgabanner.BGABanner;
import cn.efarm360.com.dabaomvp.R;

import static java.lang.System.load;

public class PictureLunBoActivity extends Activity {
//    List<String> images = new ArrayList<String>();
Integer[] images={R.mipmap.ic_launcher,R.drawable.banner2,R.drawable.header};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_lun_bo);

        BGABanner mContentBanner = (BGABanner) findViewById(R.id.banner_guide_content);

        TextView tv_more = (TextView) findViewById(R.id.tv_more);
        tv_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(PictureLunBoActivity.this, "你好啊", Toast.LENGTH_SHORT).show();

            }
        });

        mContentBanner.setAutoPlayAble(true);
        mContentBanner.setAutoPlayInterval(2000);
        mContentBanner.setData(R.drawable.banner2, R.drawable.header, R.drawable.ic_launcher);

        mContentBanner.setDelegate(new BGABanner.Delegate<ImageView, String>() {
            @Override
            public void onBannerItemClick(BGABanner banner, ImageView itemView, String model, int position) {
                Toast.makeText(banner.getContext(), "点击了" + position, Toast.LENGTH_SHORT).show();
            }
        });

          //配置数据源的方式1：通过传入数据模型并结合 Adapter 的方式配置数据源。这种方式主要用于加载网络图片，以及实现少于3页时的无限轮播
//        mContentBanner.setAdapter(new BGABanner.Adapter<ImageView, String>() {
//            @Override
//            public void fillBannerItem(BGABanner banner, ImageView itemView, String model, int position) {
//                Glide.with(PictureLunBoActivity.this)
//                        .load(model)
//                        .placeholder(R.drawable.banner2)
//                        .error(R.drawable.banner2)
//                        .centerCrop()
//                        .dontAnimate()
//                        .into(itemView);
//            }
//        });
//
//        mContentBanner.setData(Arrays.asList("网络图片路径1", "网络图片路径2", "网络图片路径3"), Arrays.asList("提示文字1", "提示文字2", "提示文字3"));


        //配置数据源的方式2：通过直接传入视图集合的方式配置数据源，主要用于自定义引导页每个页面布局的情况
//        List<View> views = new ArrayList<>();
//        views.add(BGABannerUtil.getItemImageView(this, R.drawable.ic_guide_1));
//        views.add(BGABannerUtil.getItemImageView(this, R.drawable.ic_guide_2));
//        views.add(BGABannerUtil.getItemImageView(this, R.drawable.ic_guide_3));
//        mContentBanner.setData(views);
    }
}
