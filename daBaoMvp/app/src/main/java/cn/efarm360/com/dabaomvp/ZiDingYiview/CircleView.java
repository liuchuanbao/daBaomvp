package cn.efarm360.com.dabaomvp.ZiDingYiview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import cn.efarm360.com.dabaomvp.R;

/**
 用于绘制自定义View的具体内容
具体绘制是在复写的onDraw（）内实现

 圆形view
 */

public class CircleView extends View {

  // 设置画笔变量
    Paint mPaint1;
    int mColor;

    //自定义view有四个构造函数
    // 如果View是在java代码里面new 的，则调用第一个构造函数

    public CircleView(Context context) {
        super(context);
        //在构造函数里初始化笔画的操作
        init();
    }



    // 如果View是在.xml里声明的，则调用第二个构造函数
// 自定义属性是从AttributeSet参数传进来的
    public CircleView(Context context, AttributeSet attrs) {
//        super(context, attrs);
        this(context, attrs,0);
        init();
    }
    // 不会自动调用
// 一般是在第二个构造函数里主动调用
// 如View有style属性时
    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // 加载自定义属性集合CircleView
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CircleView);

        // 解析集合中的属性circle_color属性
        // 该属性的id为:R.styleable.CircleView_circle_color
        // 将解析的属性传入到画圆的画笔颜色变量当中（本质上是自定义画圆画笔的颜色）
        // 第二个参数是默认设置颜色（即无指定circle_color情况下使用）
        mColor = a.getColor(R.styleable.CircleView_circle_color, Color.RED);

        // 解析后释放资源
        a.recycle();


        init();
    }

    //API21之后才使用
    // 不会自动调用
    // 一般是在第二个构造函数里主动调用
    // 如View有style属性时
//    public  CircleView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//    }

    // 笔画初始化
    private void init() {
        //创建笔画
     mPaint1 = new Paint();
        // 设置笔颜色为蓝色
//        mPaint1.setColor(Color.BLUE);
        mPaint1.setColor(mColor);
        //设置笔的宽度为10px
        mPaint1.setStrokeWidth(5f);
//        设置画笔模式为填充
   mPaint1.setStyle(Paint.Style.FILL);
    }

//     复写onDraw()进行绘制

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        //获取控件的高度与宽度
//        int  width = getWidth();
//        int height = getHeight();
////        设置圆的半径 = 宽，高的最小值得二分之一
//        int r = Math.min(width,height)/2;
//
////        画出圆形
////    圆心 = 控件的中央，半径 =  = 宽，高的最小值得二分之一
//
//        canvas.drawCircle(width/2,height/2,r,mPaint1);
        /**
         * 解决padding的实现
         */

        // 获取传入的padding值
        final int paddingLeft = getPaddingLeft();
        final int paddingRight = getPaddingRight();
        final int paddingTop = getPaddingTop();
        final int paddingBottom = getPaddingBottom();


        // 获取绘制内容的高度和宽度（考虑了四个方向的padding值）
        int width = getWidth() - paddingLeft - paddingRight ;
        int height = getHeight() - paddingTop - paddingBottom ;

        // 设置圆的半径 = 宽,高最小值的2分之1
        int r = Math.min(width, height)/2;

        // 画出圆(蓝色)
        // 圆心 = 控件的中央,半径 = 宽,高最小值的2分之1
        canvas.drawCircle(paddingLeft+width/2,paddingTop+height/2,r,mPaint1);


    }

    /**
     * warp_content 的解决方案
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);


        // 获取宽-测量规则的模式和大小
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        // 获取高-测量规则的模式和大小
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        // 设置wrap_content的默认宽 / 高值
        // 默认宽/高的设定并无固定依据,根据需要灵活设置
        // 类似TextView,ImageView等针对wrap_content均在onMeasure()对设置默认宽 / 高值有特殊处理,具体读者可以自行查看
        int mWidth = 400;
        int mHeight = 400;

        // 当布局参数设置为wrap_content时，设置默认值
        if (getLayoutParams().width == ViewGroup.LayoutParams.WRAP_CONTENT && getLayoutParams().height == ViewGroup.LayoutParams.WRAP_CONTENT) {
            setMeasuredDimension(mWidth, mHeight);
            // 宽 / 高任意一个布局参数为= wrap_content时，都设置默认值
        } else if (getLayoutParams().width == ViewGroup.LayoutParams.WRAP_CONTENT) {
            setMeasuredDimension(mWidth, heightSize);
        } else if (getLayoutParams().height == ViewGroup.LayoutParams.WRAP_CONTENT) {
            setMeasuredDimension(widthSize, mHeight);
        }
    }
}
