package cn.efarm360.com.dabaomvp.demos.gradationscroll.activitys;

import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import cn.efarm360.com.dabaomvp.R;
import cn.efarm360.com.dabaomvp.demos.gradationscroll.StatusBarUtil;
import cn.efarm360.com.dabaomvp.demos.gradationscroll.view.GradationScrollView;
import cn.efarm360.com.dabaomvp.demos.gradationscroll.view.MaterialIndicator;

public class BannerActivity extends AppCompatActivity implements GradationScrollView.ScrollViewListener {
    private GradationScrollView scrollView;
    private ListView listView;
    private TextView textView;
    private int imageHeight;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        StatusBarUtil.setImgTransparent(this);
        setContentView(R.layout.activity_banner);

        scrollView = (GradationScrollView) findViewById(R.id.scrollview);
        listView = (ListView) findViewById(R.id.listview);
        textView = (TextView) findViewById(R.id.textview);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        viewPager.setFocusable(true);
        viewPager.setFocusableInTouchMode(true);
        viewPager.requestFocus();

        MaterialIndicator indicator = (MaterialIndicator) findViewById(R.id.indicator);
//        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new MyPagerAdapter());
        viewPager.addOnPageChangeListener(indicator);
        indicator.setAdapter(viewPager.getAdapter());

        initListeners();
        initData();
    }

    /**
     * viewpager适配器
     */
    private  class MyPagerAdapter extends PagerAdapter {
        public int[] drawables = {R.drawable.img1
                ,R.drawable.img2,R.drawable.img3,R.drawable.img4};
        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return object == view;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView view = new ImageView(container.getContext());
            view.setImageResource(drawables[position]);
            view.setScaleType(ImageView.ScaleType.FIT_XY);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(((View) object));
        }
    }

    /**
     * 获取顶部图片高度后，设置滚动监听
     */
    private void initListeners() {

//       ViewTreeObserver 注：简单的说，这是个view事件的观察者。要注意的是它的初始化就是调用View.getViewTreeObserver()。
        /**
         * .public void addOnDrawListener (ViewTreeObserver.OnDrawListener listener)

         /**注册一个回调函数，当在一个视图树绘制时调用这个回调函数。
         * 参数 listener    将要被添加的回调函数
         *异常 IllegalStateException       如果isAlive() 返回false
         */
        //2.public void addOnGlobalFocusChangeListener (ViewTreeObserver.OnGlobalFocusChangeListener listener)

/**注册一个回调函数，当在一个视图树中的焦点状态发生改变时调用这个回调函数。
 * 参数 listener    将要被添加的回调函数
 *异常 IllegalStateException       如果isAlive() 返回false
 */
        // 3.public void addOnGlobalLayoutListener (ViewTreeObserver.OnGlobalLayoutListener listener)

/**注册一个回调函数，当在一个视图树中全局布局发生改变或者视图树中的某个视图的可视状态发生改变时调用这个回调函数。
 *参数 listener    将要被添加的回调函数
 *异常 IllegalStateException       如果isAlive() 返回false
 */
        //  4.public void addOnPreDrawListener (ViewTreeObserver.OnPreDrawListener listener)

/**注册一个回调函数，当一个视图树将要绘制时调用这个回调函数。
 *参数 listener    将要被添加的回调函数
 *异常 IllegalStateException       如果isAlive() 返回false
 */
        //   5.public void addOnScrollChangedListener (ViewTreeObserver.OnScrollChangedListener listener)
//
/**注册一个回调函数，当一个视图发生滚动时调用这个回调函数。
 *参数 listener    将要被添加的回调函数
 *异常 IllegalStateException       如果isAlive() 返回false
 */
        //    6.public void addOnTouchModeChangeListener (ViewTreeObserver.OnTouch3.监听焦点的变化ModeChangeListener listener)

/**注册一个回调函数，当一个触摸模式发生改变时调用这个回调函数。
 *参数 listener    将要被添加的回调函数
 *异常 IllegalStateException       如果isAlive() 返回false
 */
        //  7.public final void dispatchOnDraw ()

//告知相应监听器，视图绘制开始了
        //  8.public final void dispatchOnGlobalLayout ()

//当整个布局发生改变时通知相应的注册监听器。如果你强制对视图布局或者在一个没有附加到一个窗口的视图的层次结构或者在GONE状态下，它可以被手动的调用
        //  9.public final boolean dispatchOnPreDraw ()

/**当一个视图树将要绘制时通知相应的注册监听器。如果这个监听器返回true，则这个绘制将被取消并重新计划。如果你强制对视图布局或者在一个没有附加到一个窗口的视图的层次结构或者在一个GONE状态下，它可以被手动的调用
 *返回值  当前绘制能够取消并重新计划则返回true，否则返回false。
 */
//        10.public boolean isAlive ()

/**指示当前的ViewTreeObserver是否可用(alive)。当observer不可用时，任何方法的调用（除了这个方法）都将抛出一个异常。如果一个应用程序保持和ViewTreeObserver一个历时较长的引用，它应该总是需要在调用别的方法之前去检测这个方法的返回值。
 *返回值 但这个对象可用则返回true，否则返回false
 */
//        11.public void removeGlobalOnLayoutListener (ViewTreeObserver.OnGlobalLayoutListener victim)

/**移除之前已经注册的全局布局回调函数。
 *参数 victim 将要被移除的回调函数
 *异常 IllegalStateException       如果isAlive() 返回false
 */
        // 12.public void removeOnDrawListener (ViewTreeObserver.OnDrawListener victim)

/**移除之前已经注册的视图绘制回调函数。
 *参数 victim 将要被移除的回调函数
 *异常 IllegalStateException       如果isAlive() 返回false
 */
        //13.public void removeOnGlobalFocusChangeListener (ViewTreeObserver.OnGlobalFocusChangeListener victim)

/**移除之前已经注册的焦点改变回调函数。
 *参数 victim 将要被移除的回调函数
 *异常 IllegalStateException       如果isAlive() 返回false
 */
        //  14.public void removeOnPreDrawListener (ViewTreeObserver.OnPreDrawListener victim)

/**移除之前已经注册的预绘制回调函数。
 *参数 victim 将要被移除的回调函数
 *异常 IllegalStateException       如果isAlive() 返回false
 */
        //15.public void removeOnScrollChangedListener (ViewTreeObserver.OnScrollChangedListener victim)

/**移除之前已经注册的滚动改变回调函数。
 *参数 victim 将要被移除的回调函数
 *异常 IllegalStateException       如果isAlive() 返回false
 */
        //16.public void removeOnTouchModeChangeListener (ViewTreeObserver.OnTouchModeChangeListener victim)

/**移除之前已经注册的触摸模式改变回调函数
 *参数 victim 将要被移除的回调函数
 *异常 　IllegalStateException       如果isAlive() 返回false
 */

//        要获取控件的高度，视图还没显示出来高度是0，用ViewTreeObserver这个方法可以显示出视图的时候才有控件的高度，才可以调用后面的点；
        ViewTreeObserver vto = viewPager.getViewTreeObserver();
//        当在一个视图树中全局布局发生改变或者视图树中的某个视图的可视状态发生改变时，所要调用的回调函数的接口类
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                viewPager.getViewTreeObserver().removeGlobalOnLayoutListener(
                        this);
                imageHeight = viewPager.getHeight();
//               设置滚动监听
                scrollView.setScrollViewListener(BannerActivity.this);
            }
        });
    }



    private void initData() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(BannerActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.data));
        listView.setAdapter(adapter);
    }


    /**
     * 滑动监听
     * @param scrollView
     * @param x  当前横向滑动距离
     * @param y  当前纵向滑动距离
     * @param oldx 之前横向滑动距离
     * @param oldy 之前纵向滑动距离
     */
    @Override
    public void onScrollChanged(GradationScrollView scrollView, int x, int y,
                                int oldx, int oldy) {
        // TODO Auto-generated method stub
        if (y <= 0) {   //设置标题的背景颜色
            textView.setBackgroundColor(Color.argb((int) 0, 144,151,166));
        } else if (y > 0 && y <= imageHeight) { //滑动距离小于banner图的高度时，设置背景和字体颜色颜色透明度渐变
            float scale = (float) y / imageHeight;
            float alpha = (255 * scale);
            textView.setTextColor(Color.argb((int) alpha, 255,255,255));
            textView.setBackgroundColor(Color.argb((int) alpha, 144,151,166));
        } else {    //滑动到banner下面设置普通颜色
            textView.setBackgroundColor(Color.argb((int) 255, 144,151,166));
        }
    }
}
