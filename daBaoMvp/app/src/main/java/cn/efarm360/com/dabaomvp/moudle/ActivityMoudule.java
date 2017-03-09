package cn.efarm360.com.dabaomvp.moudle;

import android.content.Context;

import cn.efarm360.com.dabaomvp.bean.Persion;
import dagger.Module;
import dagger.Provides;

/**
 * 下层Module类
 */
@Module
public class ActivityMoudule {

    @Provides
    Persion providePerson(Context context){
        //　此方法需要Context 对象
        return new Persion(context);
    }
}
