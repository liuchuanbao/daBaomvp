package cn.efarm360.com.dabaomvp.activity.picture;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.PopupWindow;

import cn.efarm360.com.dabaomvp.R;

/**
 * 自定义的popupWindow
 * Created by pubinfo on 2017/6/12.
 */

public class MyPopupWindow implements PopupWindow.OnDismissListener,
        Animation.AnimationListener {

private PopupWindow.OnDismissListener dismissListener;

    private PopupWindow popWindow;
    private Context mContext;

    private View parentView;
    private ViewGroup rootViewGroup;
    private View dimView;
    private Point position;

    private int maxWindowWidth, maxWindowHeight;

    private int topLeftBackgroundDrawableId;
    private int topRightBackgroundDrawableId;
    private int bottomLeftBackgroundDrawableId;
    private int bottomRightBackgroundDrawableId;
    private int widthGap, heightGap, marginTop;

    private boolean isDimBackGroud;
    private Animation dimAnimationIn, dimAnimationOut;

    private boolean isViewRemoved;

    public MyPopupWindow(Context context, View parentView) {
        mContext = context;
        this.parentView = parentView;
        init(false);
    }

    public MyPopupWindow(Context context) {
        mContext = context;
        init(false);
    }

    public MyPopupWindow(Activity context, View parentView, boolean isFillParent) {
        mContext = context;
        this.parentView = parentView;
        init(isFillParent);
    }
    private void init(boolean isFillParent){
       if(isFillParent){
           popWindow = new PopupWindow(parentView, ViewGroup.LayoutParams.FILL_PARENT,
                   ViewGroup.LayoutParams.FILL_PARENT,true);
       }else {
           popWindow = new PopupWindow(mContext);
       }

        popWindow.setFocusable(true);
        popWindow.setTouchable(true); // 设置后外部点击将会让popwindow消失
        popWindow.setBackgroundDrawable(new BitmapDrawable());
        popWindow.setOnDismissListener(this);
       //位置
        position = new Point();

        topLeftBackgroundDrawableId = R.drawable.pop_menu_top_left;
        topRightBackgroundDrawableId = R.drawable.pop_menu_top_right;
        bottomLeftBackgroundDrawableId = R.drawable.pop_menu_bottom_left;
        bottomRightBackgroundDrawableId = R.drawable.pop_menu_bottom_right;
        widthGap = 8;
        heightGap = 20;

        dimView = LayoutInflater.from(mContext).inflate(R.layout.dim_layout,null);
        //进出动画的初始化（AlphaAnimation 渐隐渐显示）
        dimAnimationIn = new AlphaAnimation(0.0f, 1.0f);
        dimAnimationIn.setDuration(600);
        dimAnimationOut = new AlphaAnimation(1.0f, 0.0f);
        dimAnimationOut.setDuration(600);
        dimAnimationOut.setAnimationListener(this);

        isViewRemoved = true;
    }
    //设置背景资源
    public void setBackgroundDrawable(Drawable background) {
        popWindow.setBackgroundDrawable(background);
    }

    //设置背景资源id
    public void setBackgroundDrawableId(int drawableId) {
        popWindow.setBackgroundDrawable(mContext.getResources().getDrawable(
                drawableId));
    }

    //设置动画风格
    public void setAnimationStyle(int animationStyle) {
        popWindow.setAnimationStyle(animationStyle);
    }

    //设置宽度
    public void setMaxWindowWith(int maxWidth) {
        maxWindowWidth = maxWidth;
    }
    //设置高度
    public void setMaxWindowHeight(int maxHeight) {
        maxWindowHeight = maxHeight;
    }

    //设置大小
    public void setWindowSize(int width, int height) {
        setWindowWidth(width);
        setWindowHeight(height);
    }

    public void setWindowWidth(int width) {
        if (maxWindowWidth > 0) {
            popWindow.setWidth(width > maxWindowWidth ? maxWindowWidth : width);
        } else {
            popWindow.setWidth(width);
        }
    }
    public void setWindowHeight(int height) {
        if (maxWindowHeight > 0) {
            popWindow.setHeight(height > maxWindowHeight ? maxWindowHeight : height);
        } else {
            popWindow.setHeight(height);
        }
    }

    //设置上下文
    public void setContentView(View contentView) {
        popWindow.setContentView(contentView);

        // 自适应宽高
        contentView.measure(View.MeasureSpec.UNSPECIFIED,
                View.MeasureSpec.UNSPECIFIED);
        popWindow.setWidth(contentView.getMeasuredWidth() + widthGap);
        popWindow.setHeight(contentView.getMeasuredHeight() + heightGap);
    }

//触发触摸
    public void setOutsideTouchable(boolean touchable) {
        popWindow.setOutsideTouchable(touchable);
    }

    //设置dismisss监听
    public void setDismissListener(PopupWindow.OnDismissListener dismissListener) {
        this.dismissListener = dismissListener;
    }
    // 显示在底部
    public void showAsDropDown() {
        if (null == parentView || popWindow.isShowing())
            return;
        if(!isViewRemoved && isDimBackGroud)
            return;
        addDimView();
        popWindow.showAsDropDown(parentView, position.x, position.y);
    }

    //显示在本地 自定义位置
    public void showAsLocation(int gravity, int xOffset, int yOffset) {
        if (null == parentView || popWindow.isShowing())
            return;
        if(!isViewRemoved && isDimBackGroud)
            return;
        addDimView();
        popWindow.showAtLocation(parentView, gravity, xOffset, yOffset);
    }

    //添加布局
    private void addDimView() {
        try {
            if (isViewRemoved && isDimBackGroud) {
                if (null == rootViewGroup) {
                    rootViewGroup = (ViewGroup) ((Activity) mContext).getWindow().getDecorView();
                }

                // 通过添加view的形式
                rootViewGroup.addView(dimView, ViewGroup.LayoutParams.FILL_PARENT,
                        ViewGroup.LayoutParams.FILL_PARENT);

                isViewRemoved = false;
                dimView.startAnimation(dimAnimationIn);
            }
        } catch (Exception e) {
            e.printStackTrace();
//            LogUtil.i("adddimview", "", e);
        }
    }
    public void showNearParentViewAuto() {
        if (null == parentView || popWindow.isShowing())
            return;

        int[] onWindowlocation = new int[2];
        int[] onScreenlocation = new int[2];
        parentView.getLocationInWindow(onWindowlocation);
        parentView.getLocationOnScreen(onScreenlocation);
        Point p = new Point();
        p.x = onScreenlocation[0];
        p.y = onScreenlocation[1] - popWindow.getHeight();
        final Rect displayFrame = new Rect();
        parentView.getWindowVisibleDisplayFrame(displayFrame);
        int gravity;
        // final View root = parentView.getRootView();
        // 默认在头上
        if (popWindow.getHeight() + marginTop < onScreenlocation[1]) {

            boolean isTopRight = ((displayFrame.right - onScreenlocation[0] - parentView
                    .getWidth()) < (onScreenlocation[0] - displayFrame.left));
            if (!isTopRight) {
                popWindow.setBackgroundDrawable(mContext.getResources()
                        .getDrawable(this.topLeftBackgroundDrawableId));
                gravity = Gravity.LEFT | Gravity.TOP;
            } else {
                popWindow.setBackgroundDrawable(mContext.getResources()
                        .getDrawable(this.topRightBackgroundDrawableId));
                gravity = Gravity.RIGHT | Gravity.TOP;
                p.x = displayFrame.right - onScreenlocation[0]
                        - parentView.getWidth();
            }

        }
        // 否则显示在下面
        else {
            boolean isBottomRight = ((displayFrame.right - onScreenlocation[0] - parentView
                    .getWidth()) < (onScreenlocation[0] - displayFrame.left));
            if (!isBottomRight) {
                popWindow.setBackgroundDrawable(mContext.getResources()
                        .getDrawable(this.bottomLeftBackgroundDrawableId));
                gravity = Gravity.LEFT | Gravity.TOP;
                p.y += (parentView.getHeight() + popWindow.getHeight());
            } else {
                popWindow.setBackgroundDrawable(mContext.getResources()
                        .getDrawable(this.bottomRightBackgroundDrawableId));
                gravity = Gravity.RIGHT | Gravity.TOP;
                p.y += (parentView.getHeight() + popWindow.getHeight());
                p.x = displayFrame.right - onScreenlocation[0]
                        - parentView.getWidth();
            }

        }
        addDimView();
        popWindow.showAtLocation(parentView, gravity, p.x + position.x, p.y
                + position.y);
    }

    public boolean isShowing() {
        return popWindow.isShowing();
    }

    public void dismiss() {
        if (popWindow.isShowing()) {
            popWindow.dismiss();
        }
    }


    public View getParentView() {
        return parentView;
    }

    public void setParentView(View parentView) {
        this.parentView = parentView;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public int getWidthGap() {
        return widthGap;
    }

    public void setWidthGap(int widthGap) {
        this.widthGap = widthGap;
    }

    public int getHeightGap() {
        return heightGap;
    }

    public void setHeightGap(int heightGap) {
        this.heightGap = heightGap;
    }

    public int getMarginTop() {
        return marginTop;
    }

    public void setMarginTop(int marginTop) {
        this.marginTop = marginTop;
    }

    public void setRootViewGroup(ViewGroup rootViewGroup) {
        this.rootViewGroup = rootViewGroup;
    }


    public void setDimBackGroud(boolean isDimBackGroud) {
        this.isDimBackGroud = isDimBackGroud;
        popWindow.setBackgroundDrawable(new BitmapDrawable());
    }

    //动画的开始
    @Override
    public void onAnimationStart(Animation animation) {

    }
    //动画的结束
    @Override
    public void onAnimationEnd(Animation animation) {
        if (dimAnimationOut == animation) {
            rootViewGroup.removeView(dimView);
            isViewRemoved = true;
        }
    }
    //动画的重复
    @Override
    public void onAnimationRepeat(Animation animation) {

    }
    //重载onDismiss()方法
    @Override
    public void onDismiss() {
        if (null != rootViewGroup && null != dimView)
            dimView.startAnimation(dimAnimationOut);
        if (dismissListener != null) {
            dismissListener.onDismiss();
        }
    }
}
