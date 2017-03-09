package cn.efarm360.com.dabaomvp.moudle;

import android.content.Context;

import javax.inject.Singleton;

import cn.efarm360.com.dabaomvp.bean.Persion;
import dagger.Module;
import dagger.Provides;

/**
 * Created by pubinfo on 2017/3/1.
 */

@Module   //提供依赖对象的实例
public class PersionModule {
/**   @Provides // 关键字，标明该方法提供依赖对象 方法可以提供被注入的类实例。
//    @Singleton  //单例模式
//    Persion providerPerson(){
//        //提供Person对象
//        return new Persion();
  }*/
private Context mContext;

    public PersionModule(Context context){
        mContext = context;
    }
    @Provides // 方法可以提供被注入的类实例。
    Context providesContext(){
        // 提供上下文对象
        return mContext;
    }
    @Provides // 关键字，标明该方法提供依赖对象
    @Singleton
    Persion providerPerson(Context context){

        return new Persion(context);
    }


}
