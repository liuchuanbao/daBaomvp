package cn.efarm360.com.dabaomvp.utils;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

/**
 * Created by pubinfo on 2017/4/20.
 * 给屏幕增加一个蒙层
     */

public class AddLayerUtil {


    /* 为rootView添加蒙层
    * @return
            */
    public static void addLayer(Activity mContext, View layerView) {
        if (mContext == null || layerView == null)
            return;
        ViewGroup contentView = (ViewGroup) mContext.getWindow().findViewById(Window.ID_ANDROID_CONTENT);
        contentView.addView(layerView);
    }
    /* 为rootView删除蒙层
       * @return
               */
    public static void deleteLayer(Activity mContext, View layerView) {
        if (mContext == null || layerView == null)
            return;
        ViewGroup contentView = (ViewGroup) mContext.getWindow().findViewById(Window.ID_ANDROID_CONTENT);
        contentView.removeView(layerView);
    }
}
