package cn.efarm360.com.dabaomvp.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import cn.efarm360.com.dabaomvp.R;
import cn.efarm360.com.dabaomvp.adapter.TabFragmentAdapter;
import cn.efarm360.com.dabaomvp.fragement.TabFragment;

import static cn.efarm360.com.dabaomvp.R.id.viewPager;


/**
 * 演示 TabLayout常用的方法如下：
 - addTab(TabLayout.Tab tab, int position, boolean setSelected) 增加选项卡到 layout 中
 - addTab(TabLayout.Tab tab, boolean setSelected) 同上
 - addTab(TabLayout.Tab tab) 同上
 - getTabAt(int index) 得到选项卡
 - getTabCount() 得到选项卡的总个数
 - getTabGravity() 得到 tab 的 Gravity
 - getTabMode() 得到 tab 的模式
 - getTabTextColors() 得到 tab 中文本的颜色
 - newTab() 新建个 tab
 - removeAllTabs() 移除所有的 tab
 - removeTab(TabLayout.Tab tab) 移除指定的 tab
 - removeTabAt(int position) 移除指定位置的 tab
 - setOnTabSelectedListener(TabLayout.OnTabSelectedListener onTabSelectedListener) 为每个 tab 增加选择监听器
 - setScrollPosition(int position, float positionOffset, boolean updateSelectedText) 设置滚动位置
 - setTabGravity(int gravity) 设置 Gravity
 - setTabMode(int mode) 设置 Mode,有两种值：TabLayout.MODE_SCROLLABLE和TabLayout.MODE_FIXED分别表示当tab的内容超过屏幕宽度是否支持横向水平滑动，第一种支持滑动，第二种不支持，默认不支持水平滑动。
 - setTabTextColors(ColorStateList textColor) 设置 tab 中文本的颜色
 - setTabTextColors(int normalColor, int selectedColor) 设置 tab 中文本的颜色 默认 选中
 - setTabsFromPagerAdapter(PagerAdapter adapter) 设置 PagerAdapter
 - setupWithViewPager(ViewPager viewPager) 和 ViewPager 联动

 一般TabLayout都是和ViewPager共同使用才发挥它的优势，现在我们通过代码来看看以上方法的使用。
 */
public class TablayoutActivity extends BaseActivity {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_tablayout);
//    }
private TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    protected int getContentView() {
        return R.layout.activity_tablayout;
    }

    @Override
    protected void initView() {
        initTabLayout();
    }
    private void initTabLayout() {
        viewPager = findView(R.id.viewPager);

        tabLayout = findView(R.id.tabs);
        List<String> tabList = new ArrayList<>();
        tabList.add("Tab1");
        tabList.add("Tab2");
        tabList.add("Tab3");
        tabList.add("Tab4");
        tabList.add("Tab5");
        tabList.add("Tab6");
        tabList.add("Tab7");
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);//设置tab模式，当前为系统默认模式
        //此处代码设置无效，不知道为啥？？？xml属性是可以的
//        tabLayout.setTabTextColors(android.R.color.white, android.R.color.holo_red_dark);//设置TabLayout两种状态
        tabLayout.addTab(tabLayout.newTab().setText(tabList.get(0)));//添加tab选项卡
        tabLayout.addTab(tabLayout.newTab().setText(tabList.get(1)));
        tabLayout.addTab(tabLayout.newTab().setText(tabList.get(2)));
        tabLayout.addTab(tabLayout.newTab().setText("Tab4"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab5"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab6"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab7"));

        List<Fragment> fragmentList = new ArrayList<>();
        for (int i = 0; i < tabList.size(); i++) {
            Fragment f1 = new TabFragment();
            Bundle bundle = new Bundle();
            bundle.putString("content", "http://blog.csdn.net/feiduclear_up \n CSDN 废墟的树");
            f1.setArguments(bundle);
            fragmentList.add(f1);
        }

        TabFragmentAdapter fragmentAdapter = new TabFragmentAdapter(getSupportFragmentManager(), fragmentList, tabList);
        viewPager.setAdapter(fragmentAdapter);//给ViewPager设置适配器
        tabLayout.setupWithViewPager(viewPager);//将TabLayout和ViewPager关联起来。
        tabLayout.setTabsFromPagerAdapter(fragmentAdapter);//给Tabs设置适配器

    }
}
