package cn.efarm360.com.dabaomvp.moudle;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by pubinfo on 2017/3/1.
 * 在AppMoudle中能够提供Context对象
 */
@Module
public class AppModule {

    private Context mContext;

    public AppModule(Context context){
        mContext = context;
    }

    @Provides
    Context providesContext(){
        // 提供Context对象　
        return mContext;
    }

}
