package cn.efarm360.com.dabaomvp.ZiDingYiview;

import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import cn.efarm360.com.dabaomvp.R;

/**
 * 自定义可以实现拖拽的View
 * 我们列一下所有的Callback方法，看看还有哪些没用过的：

 onViewDragStateChanged

 当ViewDragHelper状态发生变化时回调（IDLE,DRAGGING,SETTING[自动滚动时]）
 onViewPositionChanged

 当captureview的位置发生改变时回调
 onViewCaptured

 当captureview被捕获时回调
 onViewReleased 已用

 onEdgeTouched

 当触摸到边界时回调。
 onEdgeLock

 true的时候会锁住当前的边界，false则unLock。
 onEdgeDragStarted 已用

 getOrderedChildIndex

 改变同一个坐标（x,y）去寻找captureView位置的方法。（具体在：findTopChildUnder方法中）
 getViewHorizontalDragRange 已用

 getViewVerticalDragRange 已用
 tryCaptureView 已用
 clampViewPositionHorizontal 已用
 clampViewPositionVertical 已用
 ok，至此所有的回调方法都有了一定的认识。
 */

public class VDHLinearLayout2 extends LinearLayout {

    ViewDragHelper dragHelper;

    View dragView;
    View edgeDragView;
    View autoBackView;


    public VDHLinearLayout2(Context context) {
        super(context);
    }

    public VDHLinearLayout2(Context context, AttributeSet attrs) {
        super(context, attrs);
        //1、创建实例  创建实例需要3个参数，第一个就是当前的ViewGroup，第二个sensitivity（敏感度），主要用于设置touchSlop:
        dragHelper = ViewDragHelper.create(this,1.0f, new ViewDragHelper.Callback() {
           //tryCaptureView如何返回ture则表示可以捕获该view，你可以根据传入的第一个view参数决定哪些可以捕获
            @Override
            public boolean tryCaptureView(View child, int pointerId) {

                return child == dragView||child ==autoBackView;
            }
//可以在该方法中对child移动的边界进行控制，left , top 分别为即将移动到的位置，比如横向的情况下，我希望只在ViewGroup的内部移动，
// 即：最小>=paddingleft，最大<=ViewGroup.getWidth()-paddingright-child.getWidth。就可以按照如下代码编写：
            @Override
            public int clampViewPositionVertical(View child, int top, int dy) {
                return top;
            }
            //可以在该方法中对child移动的边界进行控制，left , top 分别为即将移动到的位置，比如横向的情况下，我希望只在ViewGroup的内部移动，
            // 即：最小>=paddingleft，最大<=ViewGroup.getWidth()-paddingright-child.getWidth。就可以按照如下代码编写：
            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
                return left;
            }
            // 当前被捕获的View释放之后回调
            @Override
            public void onViewReleased(View releasedChild, float xvel, float yvel) {
                //mAutoBackView手指释放时可以自动回去
                if (releasedChild == autoBackView)
                {
                    dragHelper.settleCapturedViewAt(autoBackViewOriginLeft, autoBackViewOriginTop);
                    invalidate();
                }
            }

            /**
             *，如果子View不消耗事件，那么整个手势（DOWN-MOVE*-UP）都是直接进入onTouchEvent，
             * 在onTouchEvent的DOWN的时候就确定了captureView。如果消耗事件，那么就会先走onInterceptTouchEvent方法，
             * 判断是否可以捕获，而在判断的过程中会去判断另外两
             * 个回调的方法：getViewHorizontalDragRange和getViewVerticalDragRange，只有这两个方法返回大于0的值才能正常的捕获。
             * @param edgeFlags
             * @param pointerId
             */
            //在边界拖动时回调
            @Override
            public void onEdgeDragStarted(int edgeFlags, int pointerId) {
                dragHelper.captureChildView(edgeDragView, pointerId);
            }

//            如果你用Button测试，或者给TextView添加了clickable = true ，都记得重写下面这两个方法：
            @Override
            public int getViewVerticalDragRange(View child) {
                return getMeasuredHeight() - child.getMeasuredHeight();
            }
//            如果你用Button测试，或者给TextView添加了clickable = true ，都记得重写下面这两个方法：
            @Override
            public int getViewHorizontalDragRange(View child) {
                return getMeasuredWidth() - child.getMeasuredWidth();
            }
        });
        // 设置左边缘可以被Drag
        dragHelper.setEdgeTrackingEnabled(ViewDragHelper.EDGE_LEFT);
    }

    //2、触摸相关方法
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return dragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        dragHelper.processTouchEvent(event);
        return true;
    }



    @Override
    public void computeScroll() {
        if (dragHelper.continueSettling(true))
        {
            //重画view
            invalidate();
        }
    }

    // 当View中所有的子控件 均被映射成xml后触发
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        dragView = findViewById(R.id.dragView);
        edgeDragView = findViewById(R.id.edgeDragView);
        autoBackView = findViewById(R.id.autoBackView);
    }

    int autoBackViewOriginLeft;
    int autoBackViewOriginTop;

    //因为onLayout的目的是确定子View在父View中的位置，那么这个步骤肯定是由父View来决定的，因此在View中onLayout是一个空的实现
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        autoBackViewOriginLeft = autoBackView.getLeft();
        autoBackViewOriginTop = autoBackView.getTop();
    }

    public VDHLinearLayout2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
