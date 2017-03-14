package cn.efarm360.com.dabaomvp.utils;

import org.xmlpull.v1.XmlPullParser;

/**
 * Created by liuchuanbao on 2017/1/22.
 */

public class StringUtil {
    public static boolean isNull(String mac) {
        if (mac == null || XmlPullParser.NO_NAMESPACE.equals(mac)) {
            return true;
        }
        return false;
    }
    public  static boolean isEquals(String a,String b){
        if(a.isEmpty()){
            return false;
        }
        if(b.isEmpty()){
            return false;
        }
        if(a.equals(b)){
            return true;
        }
        return false;
    }
}
