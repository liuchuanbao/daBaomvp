package cn.efarm360.com.dabaomvp.activity.CoordinatorLayoutTest;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import cn.efarm360.com.dabaomvp.R;

public class MaterialUpConceptActivity extends AppCompatActivity 	implements AppBarLayout.OnOffsetChangedListener{


    private static final int PERCENTAGE_TO_ANIMATE_AVATAR = 20;
    private boolean mIsAvatarShown = true;

    private ImageView mProfileImage;
    private int mMaxScrollSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_material_up_concept);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.materialup_tabs);
        ViewPager viewPager  = (ViewPager) findViewById(R.id.materialup_viewpager);
        AppBarLayout appbarLayout = (AppBarLayout) findViewById(R.id.materialup_appbar);
        mProfileImage = (ImageView) findViewById(R.id.materialup_profile_image);

        Toolbar toolbar = (Toolbar) findViewById(R.id.materialup_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                //返回
                onBackPressed();
            }
        });

        // AppBarLayout 添加自定义行为
        appbarLayout.addOnOffsetChangedListener(this);


        //表示总共可以滑动的范围
//        它是计算所有layout_scrollFlags标有scroll 的View 的高度减去所有同时标有scroll 和 exitUntilCollapsed 的 View 的最小高度.
        mMaxScrollSize = appbarLayout.getTotalScrollRange();

        //viewPager 添加adapter
        viewPager.setAdapter(new TabsAdapter(getSupportFragmentManager()));

      //将TabLayout和ViewPager关联起来。
        tabLayout.setupWithViewPager(viewPager);
    }

    // 开启自身activity
    public static void start(Context c) {
        c.startActivity(new Intent(c, MaterialUpConceptActivity.class));
    }
     /**
    * 在AppBarLayout的布局偏移量发生改变时被调用。
            * 这个方法允许子view根据偏移量实现自定义的行为（比如在特定Y值的时候固定住一个View）。
            * @param appBarLayout
    * @param i
    */
    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
        if (mMaxScrollSize == 0)
            mMaxScrollSize = appBarLayout.getTotalScrollRange();

        int percentage = (Math.abs(i)) * 100 / mMaxScrollSize;

        if (percentage >= PERCENTAGE_TO_ANIMATE_AVATAR && mIsAvatarShown) {
            mIsAvatarShown = false;

            //圆形view的缩放显示
            mProfileImage.animate()
                    .scaleY(0).scaleX(0)
                    .setDuration(200)
                    .start();
        }

        if (percentage <= PERCENTAGE_TO_ANIMATE_AVATAR && !mIsAvatarShown) {
            mIsAvatarShown = true;
          //圆形view的缩放显示
            mProfileImage.animate()
                    .scaleY(1).scaleX(1)
                    .start();
        }
    }
    private static class TabsAdapter extends FragmentPagerAdapter {
        private static final int TAB_COUNT =3;  //fragmen的个数

        TabsAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return TAB_COUNT;
        }

        @Override
        public Fragment getItem(int i) {
            return FakePageFragment.newInstance();   //返回 FakePageFragment
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "Tab " + String.valueOf(position);
        }
    }
}
