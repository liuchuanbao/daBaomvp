package cn.efarm360.com.dabaomvp.bean;

import android.content.Context;
import android.util.Log;

import javax.inject.Inject;

/**
 * Created by pubinfo on 2017/3/1.
 */

public class Persion {

    String name ;
    int age ;
//    public Persion(){
//        Log.e("dagger","person create!!!");
//    }
    private Context mContext;

    @Inject  // 添加注解关键字 提供对象
    public Persion(Context context){
        mContext = context;
        Log.e("dagger","create");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
