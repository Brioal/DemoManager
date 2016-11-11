package com.brioal.demomanager.interfaces;

import com.brioal.demomanager.bean.DemoBean;

import java.util.List;

/**
 * Created by brioal on 16-11-10.
 * Email : brioal@foxmail.com
 * Github : https://github.com/Brioal
 */

public interface OnDemoLoadListener {
    void succeed(List<DemoBean> list);

    void failed(String error);
}
