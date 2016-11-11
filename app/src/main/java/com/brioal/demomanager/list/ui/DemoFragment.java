package com.brioal.demomanager.list.ui;

import android.os.Bundle;

import com.brioal.demomanager.base.BaseFragment;

/**
 * Created by brioal on 16-11-10.
 * Email : brioal@foxmail.com
 * Github : https://github.com/Brioal
 */

public class DemoFragment extends BaseFragment {
    public static final int TYPE_LOCAL = 0;
    public static final int TYPE_NET = 1;
    private static DemoFragment sFragment;

    public static DemoFragment getInstance(int type) {
        if (sFragment == null) {
            sFragment = new DemoFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("Type", type);
            sFragment.setArguments(bundle);
        }

        return sFragment;
    }

}
