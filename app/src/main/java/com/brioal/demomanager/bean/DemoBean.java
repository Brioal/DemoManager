package com.brioal.demomanager.bean;

import android.graphics.drawable.Drawable;

import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by brioal on 16-11-10.
 * Email : brioal@foxmail.com
 * Github : https://github.com/Brioal
 */

public class DemoBean {
    private String mTitle;//Demo名称
    private String mPackage;//Package
    private Drawable mIcon; //Demo图标（只有本地数据有）
    private String mDesc;//Demo描述
    private String mVersionName;//版本名称
    private boolean isLocal = false;//是否本地存在
    private boolean hasUpdate = false;//是否有更新
    private String mNewVersionName;//新版本号
    private BmobFile mApk;
    private long mClickTime;//最近一次点击的时间

    public DemoBean setHasUpdate(boolean hasUpdate) {
        this.hasUpdate = hasUpdate;
        return this;
    }

    public DemoBean setClickTime(long clickTime) {
        mClickTime = clickTime;
        return this;
    }

    public long getClickTime() {
        return mClickTime;
    }

    public String getNewVersionName() {
        return mNewVersionName;
    }

    public DemoBean setNewVersionName(String newVersionName) {
        mNewVersionName = newVersionName;
        return this;
    }

    public boolean isHasUpdate() {
        return hasUpdate;
    }

    public DemoBean setTitle(String title) {
        mTitle = title;
        return this;
    }

    public DemoBean setPackage(String aPackage) {
        mPackage = aPackage;
        return this;
    }

    public DemoBean setIcon(Drawable icon) {
        mIcon = icon;
        return this;
    }

    public DemoBean setDesc(String desc) {
        mDesc = desc;
        return this;
    }

    public DemoBean setVersionName(String versionName) {
        mVersionName = versionName;
        return this;
    }


    public DemoBean setLocal(boolean local) {
        isLocal = local;
        return this;
    }


    public String getPackage() {
        return mPackage;
    }

    public String getTitle() {
        return mTitle;
    }

    public Drawable getIcon() {
        return mIcon;
    }

    public String getDesc() {
        return mDesc;
    }

    public String getVersionName() {
        return mVersionName;
    }

    public boolean isLocal() {
        return isLocal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        DemoBean bean = (DemoBean) o;
        return mPackage != null ? mPackage.equals(bean.mPackage) : bean.mPackage == null;

    }

    @Override
    public int hashCode() {
        return mPackage != null ? mPackage.hashCode() : 0;
    }
}
