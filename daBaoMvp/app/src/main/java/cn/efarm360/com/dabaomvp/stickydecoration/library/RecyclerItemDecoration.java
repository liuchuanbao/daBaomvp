package cn.efarm360.com.dabaomvp.stickydecoration.library;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by pubinfo on 2017/7/17.
 */

public class RecyclerItemDecoration extends RecyclerView.ItemDecoration {
//    onDraw：通过该方法，在Canvas上绘制内容，在绘制Item之前调用。（如果没有通过getItemOffsets设置偏移的话，Item的内容会将其覆盖）
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        onDraw(c, parent);
    }
//    onDrawOver：通过该方法，在Canvas上绘制内容,在Item之后调用。(画的内容会覆盖在item的上层)/
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        onDrawOver(c, parent);
    }
//    getItemOffsets：通过Rect为每个Item设置偏移，用于绘制Decoration。
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        getItemOffsets(outRect, ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition(),
                parent);
    }

}
