package com.brioal.demomanager.list.model;

import android.content.Context;

import com.brioal.demomanager.bean.DemoBean;
import com.brioal.demomanager.interfaces.OnDemoLoadListener;

import java.util.List;

/**
 * Created by brioal on 16-11-10.
 * Email : brioal@foxmail.com
 * Github : https://github.com/Brioal
 */

public interface DemoListModel {
    //获取本地的Demo
    void getDemoLocal(Context context, OnDemoLoadListener listener);

    //获取网络的Demo
    void getDemoNet(Context context, OnDemoLoadListener listener);

    //保存Demo到本地
    void saveDemoLocal(Context context, List<DemoBean> list);

}
