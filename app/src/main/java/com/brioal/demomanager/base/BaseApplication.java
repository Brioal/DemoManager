package com.brioal.demomanager.base;

import android.app.Application;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatDelegate;

import java.lang.reflect.Field;

import cn.bmob.v3.Bmob;

/**
 * Created by brioal on 16-11-10.
 * Email : brioal@foxmail.com
 * Github : https://github.com/Brioal
 */

public class BaseApplication extends Application {
    private final String mBmobAppKey = "6c4c6193ff5bf742c027d1e6c3138e3a";

    @Override
    public void onCreate() {
        super.onCreate();
        initNightTheme();
        initBmobSDK();
        initFont();
    }

    //设置全局字体
    private void initFont() {
        Typeface fz = Typeface.createFromAsset(getAssets(), "fz.ttf");
        try {
            Field field = Typeface.class.getDeclaredField("SERIF");
            field.setAccessible(true);
            field.set(null, fz);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    //初始化BmobSDK
    private void initBmobSDK() {
        Bmob.initialize(this, mBmobAppKey);
    }

    //根据当前时间初始化夜间模式
    private void initNightTheme() {
        AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_AUTO);
    }
}
