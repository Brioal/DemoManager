package com.brioal.demomanager.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by brioal on 16-11-10.
 * Email : brioal@foxmail.com
 * Github : https://github.com/Brioal
 */

public class BaseActivity<P extends BasePresenter> extends AppCompatActivity{
    private Context mContext;
    private P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mContext = this;
        super.onCreate(savedInstanceState);
    }
}
