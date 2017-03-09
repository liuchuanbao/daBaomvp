package com.example.pubinfo.dabaodagger2.modul;

import com.example.pubinfo.dabaodagger2.data.Poetry;
import com.example.pubinfo.dabaodagger2.di.AScope;
import com.example.pubinfo.dabaodagger2.di.PoetryQualifier;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by pubinfo on 2017/3/7.
 */

@Module
public class AModule {

    @PoetryQualifier("A")
    @AScope
    @Provides
    public Poetry getPoetry(){
        return new Poetry("万物美好");
    }



    @PoetryQualifier("B")
    @AScope
    @Provides
    public Poetry getOtherPoetry(){
        return new Poetry("我在中间");
    }
}
