package cn.efarm360.com.dabaomvp.utils;

import java.io.File;

/**
 * Created by liuchuanbao on 2016/12/11.
 */
public class FileUtil {
    public static boolean isExistes(String path){
        File file =new File(path);
        if(file.exists()){
            return true;
        }
        return  false;
    }
    public static boolean isExistes(String dir,String path){
        File files = new File(dir,path);
        if(files.exists()){
            return true;
        }
     return false;
    }
}
