package com.brioal.demomanager.base;

import android.content.Context;

/**
 * Created by brioal on 16-11-10.
 * Email : brioal@foxmail.com
 * Github : https://github.com/Brioal
 */

public abstract class BasePresenter<M, V> {
    private Context mContext;
    M mModel;
    V mView;

    public void setMV(M model, V view) {
        mModel = model;
        mView = view;
    }

    //绑定Context
    public void bindContext(Context context) {
        this.mContext = context;
    }

    abstract void presenterStart();

    abstract void presenterPause();

    abstract void presenterResume();

    abstract void destory();
}
