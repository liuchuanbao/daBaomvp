package com.example.pubinfo.dabaodagger2;

import android.app.Application;

import com.example.pubinfo.dabaodagger2.component.AComponent;
import com.example.pubinfo.dabaodagger2.component.ApplicationComponent;
import com.example.pubinfo.dabaodagger2.component.DaggerApplicationComponent;
import com.example.pubinfo.dabaodagger2.modul.AModule;

/**
 * 充当中间类，封装对象需要用的全局对象
 */

public class MainApplication  extends Application {
    private ApplicationComponent mApplicationComponent;
    private static MainApplication sApplication;
    private AComponent mAComponent;

    public static MainApplication getInstance() {
        return sApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;

        mApplicationComponent = DaggerApplicationComponent.builder().build();
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

    public AComponent getAComponent() {
        if (mAComponent == null){
            mAComponent = mApplicationComponent.plus(new AModule());
        }
        return mAComponent;
    }
}
