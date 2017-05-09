package cn.efarm360.com.dabaomvp.utils;

import android.content.Context;
import android.util.DisplayMetrics;


public class ScreenUtil {
	public static int screenWidth;
	public static int screenHeight;
	public static float density;
	public static float scaleDensity;
	public static float xdpi;
	public static float ydpi;
	public static int densityDpi;
	public static int screenMin;// 宽高中，最小的值

	public static void GetInfo(Context context) {
		if (null == context) {
			return;
		}
		DisplayMetrics dm = new DisplayMetrics();
		dm = context.getApplicationContext().getResources().getDisplayMetrics();
		screenWidth = dm.widthPixels;
		screenHeight = dm.heightPixels;
		screenMin = (screenWidth > screenHeight) ? screenHeight : screenWidth;
		density = dm.density;
		scaleDensity = dm.scaledDensity;
		xdpi = dm.xdpi;
		ydpi = dm.ydpi;
		densityDpi = dm.densityDpi;
	}

	public static int dip2px(float dipValue) {
		final float scale = ScreenUtil.getDisplayDensity();
		return (int) (dipValue * scale + 0.5f);
	}

	public static int px2dip(float pxValue) {
		final float scale = ScreenUtil.getDisplayDensity();
		return (int) (pxValue / scale + 0.5f);
	}

    private static float getDisplayDensity() {
        if(density == 0){
//            GetInfo(MyApplication.getInstance().getApplicationContext());
        }
        return density;
    }
	
	public static int getDisplayWidth(Context context ){
		if(screenWidth == 0){
			GetInfo(context);
		}
		return screenWidth;
	}
	
	public static int getDisplayHeight(Context context) {
		if(screenHeight == 0){
			//使用的时候 需要在  在MyApplication中创建一个全局context的对象
            GetInfo(context);
        }
		return screenHeight;
	}
}
