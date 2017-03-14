package cn.efarm360.com.dabaomvp.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences.Editor;

public class AppSharedPreferences {
    private Context context;

    public AppSharedPreferences(Context applicationContext) {
        this.context = applicationContext;
    }

    public void saveStringMessage(String fileName, String key, String value) {
        Editor edit = this.context.getSharedPreferences(fileName, 0).edit();
        edit.putString(key, value);
        edit.commit();
    }

    @SuppressLint({"WorldReadableFiles"})
    public String getStringMessage(String fileName, String key, String value) {
        return this.context.getSharedPreferences(fileName, 1).getString(key, value);
    }

    public void saveIntMessage(String fileName, String key, int value) {
        Editor edit = this.context.getSharedPreferences(fileName, 0).edit();
        edit.putInt(key, value);
        edit.commit();
    }

    @SuppressLint({"WorldReadableFiles"})
    public int getIntMessage(String fileName, String key, int defaltValve) {
        return this.context.getSharedPreferences(fileName, 1).getInt(key, defaltValve);
    }

    public void saveBooleanMessage(String fileName, String key, boolean value) {
        Editor edit = this.context.getSharedPreferences(fileName, 0).edit();
        edit.putBoolean(key, value);
        edit.commit();
    }

    @SuppressLint({"WorldReadableFiles"})
    public boolean getBooleanMessage(String fileName, String key, boolean defaltValve) {
        return this.context.getSharedPreferences(fileName, 1).getBoolean(key, defaltValve);
    }

    public void saveLongMessage(String fileName, String key, long value) {
        Editor edit = this.context.getSharedPreferences(fileName, 0).edit();
        edit.putLong(key, value);
        edit.commit();
    }

    @SuppressLint({"WorldReadableFiles"})
    public long getLongMessage(String fileName, String key, long defaltValve) {
        return this.context.getSharedPreferences(fileName, 1).getLong(key, defaltValve);
    }

    public void deleteMessage(String fileName, String key) {
        Editor edit = this.context.getSharedPreferences(fileName, 0).edit();
        edit.remove(key);
        edit.commit();
    }
}
