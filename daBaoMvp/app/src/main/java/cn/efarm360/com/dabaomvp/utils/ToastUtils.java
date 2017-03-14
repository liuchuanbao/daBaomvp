package cn.efarm360.com.dabaomvp.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by liuchuanbao on 2017/2/8.
 */

public class ToastUtils {
    public static void showLToast(Context context , String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
    }
