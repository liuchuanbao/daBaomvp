package cn.efarm360.com.dabaomvp.ZiDingYiview.ThreeD;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * 自定义的3d 显示view
 * Created by pubinfo on 2017/4/25.
 */

public class CameraTest extends ViewGroup {

    private int width;
    private int height;
    private Camera camera;
    private Matrix matrix;
    private Scroller scroller;
    private int baseAngle = 90;
    private int lastY;

    public CameraTest(Context context) {
        super(context);
        init();
    }

    public CameraTest(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public CameraTest(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CameraTest(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {

        camera = new Camera();

        matrix = new Matrix();
        scroller = new Scroller(getContext());
    }

    // 获取屏幕的高与宽，onMeasure();
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        width = getMeasuredWidth();
        height = getMeasuredHeight();
    }

    //布局
    @Override
    protected void onLayout(boolean b, int l, int i1, int i2, int i3) {
        int currentTop = 0;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            if (childView.getVisibility() != GONE) {
                int childViewWidth = childView.getMeasuredWidth();
                int childViewHeight = childView.getMeasuredHeight();
                childView.layout(0, currentTop, childViewWidth, childViewHeight + currentTop);
                currentTop += childViewHeight;
            }
        }
    }
    /**
     * 画单个页面
     * @param canvas
     * @param screen
     * @param drawingTime
     */
    @Override
    protected void dispatchDraw(Canvas canvas) {
        drawChildView(canvas);
    }
    private void drawChildView(Canvas canvas) {
        int childCount = getChildCount();
        int currentTop = 0;
        for (int i = 0; i < childCount; i++) {
            currentTop = height * i;
            long drawingTime = getDrawingTime();
            View childView = getChildAt(i);
            // 屏幕中不显示的部分不进行绘制
            if (getScrollY() + height < currentTop || currentTop < getScrollY() - height) {
                continue;
            }

            float centerX = width / 2;
            float centerY = (getScrollY() > currentTop) ? currentTop + height : currentTop;
            float degree = baseAngle * (getScrollY() - currentTop) / height;
            if (degree > baseAngle || degree < -baseAngle) {
                continue;
            }
            canvas.save();

            camera.save();
            camera.rotateX(degree);
            camera.getMatrix(matrix);
            camera.restore();

            matrix.preTranslate(-centerX, -centerY);
            matrix.postTranslate(centerX, centerY);
            canvas.concat(matrix);
            drawChild(canvas, childView, drawingTime);

            canvas.restore();
        }
    }
    // 事件拦截 true  = 响应
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        return true;
    }
    //  触摸事件
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        float currentY = event.getY();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                lastY = (int) currentY;
            case MotionEvent.ACTION_MOVE:
                float distanceY = (currentY - lastY);
                lastY = (int) currentY;
                scrollBy(0, (int) distanceY);
                recycleChildView();
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }

    //
    private void recycleChildView() {
        if (getScrollY() > (getChildCount() - 1) * height - 5) {
            addNextView();
        } else if (getScrollY() < 5) {
            addPreView();
        }
    }

    private void addNextView() {
        View firstChildView = getChildAt(0);
        int childCount = getChildCount();
        removeViewAt(0);
        addView(firstChildView, childCount - 1);
        scrollBy(0, -height);
    }

    private void addPreView() {
        int childCount = getChildCount();
        View firstChildView = getChildAt(childCount - 1);
        removeViewAt(childCount - 1);
        addView(firstChildView, 0);
        scrollBy(0, height);
    }
}
