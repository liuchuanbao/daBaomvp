package cn.efarm360.com.dabaomvp.activity.VideoPlayView.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

/**
 * Created by pubinfo on 2017/7/10.
 */

public class CustomVideoView  extends VideoView{

    int default_width ;
    int defaukt_height ;

    public CustomVideoView(Context context) {
        this(context,null);
    }

    public CustomVideoView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        default_width = context.getResources().getDisplayMetrics().widthPixels;
        defaukt_height = context.getResources().getDisplayMetrics().heightPixels;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getDefaultSize(default_width,widthMeasureSpec);
        int height = getDefaultSize(defaukt_height,heightMeasureSpec);
        setMeasuredDimension(width,height);


    }
}
