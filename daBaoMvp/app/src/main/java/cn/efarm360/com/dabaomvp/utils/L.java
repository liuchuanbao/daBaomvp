package cn.efarm360.com.dabaomvp.utils;

import android.util.Log;

public class L {
    private static final boolean flag = true;

    public static void i(String tag, String msg) {
        if (msg != null) {
            Log.e(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (msg != null) {
            Log.e(tag, msg);
        }
    }

    public static void v(String tag, String msg) {
        if (msg != null) {
            Log.e(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (msg != null) {
            Log.e(tag, msg);
        }
    }

    public static void getLongLog(String tag, String methodName, String str) {
        if (str != null) {
            try {
                v(tag, new StringBuilder(String.valueOf(methodName)).append(" str.length=").append(str.length()).toString());
                for (int i = 0; i < str.length() / 3000; i++) {
                    v(tag, new StringBuilder(String.valueOf(methodName)).append(" str ").append(i).append(" = ").append(str.substring(i * 3000, (i + 1) * 3000)).toString());
                }
                v(tag, new StringBuilder(String.valueOf(methodName)).append(" str  = ").append(str.substring((str.length() / 3000) * 3000)).toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
