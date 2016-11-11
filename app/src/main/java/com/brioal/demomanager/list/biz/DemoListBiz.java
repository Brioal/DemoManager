package com.brioal.demomanager.list.biz;

import android.content.Context;

import com.brioal.demomanager.bean.DemoBean;
import com.brioal.demomanager.interfaces.OnDemoLoadListener;
import com.brioal.demomanager.list.model.DemoListModel;
import com.brioal.demomanager.util.DataLoader;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by brioal on 16-11-10.
 * Email : brioal@foxmail.com
 * Github : https://github.com/Brioal
 */

public class DemoListBiz implements DemoListModel {
    private List<DemoBean> mLocalDemo;


    @Override
    public void getDemoLocal(final Context context, final OnDemoLoadListener listener) {
        final Subscriber subscribe = new Subscriber<List<DemoBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                if (listener != null) {
                    listener.failed(throwable.getMessage());
                }
            }

            @Override
            public void onNext(List<DemoBean> list) {
                if (list.size() == 0) {
                    if (listener != null) {
                        listener.failed("未找到数据");
                    }
                } else {
                    if (listener != null) {
                        listener.succeed(list);
                    }
                }
            }

        };
        Observable.create(new Observable.OnSubscribe<List<DemoBean>>() {
            @Override
            public void call(Subscriber<? super List<DemoBean>> subscriber) {
                List<DemoBean> demoDD = DataLoader.getInstance(context).loadDemoDB();
                List<DemoBean> demoLocal = DataLoader.getInstance(context).loadDemoLocal(context.getPackageManager());
                List<DemoBean> demo = new ArrayList<DemoBean>();
                for (int i = 0; i < demoLocal.size(); i++) {
                    DemoBean localBean = demoLocal.get(i);
                    if (demoDD.contains(localBean)) {
                        int index = demoDD.indexOf(localBean);
                        DemoBean dbBean = demoDD.get(index);
                        localBean.setDesc(dbBean.getDesc());
                    }
                    demo.add(localBean);
                }
                subscriber.onNext(demo);

            }
        }).observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(subscribe);
    }

    @Override
    public void getDemoNet(Context context, final OnDemoLoadListener listener) {
        //网络数据只包含 包名 描述 版本号 添加时间
        BmobQuery<DemoBean> query = new BmobQuery<DemoBean>();
        query.setLimit(500);
        query.findObjects(new FindListener<DemoBean>() {
            @Override
            public void done(List<DemoBean> object, BmobException e) {
                if (e == null) {
                    if (object.size() == 0) {
                        if (listener != null) {
                            listener.failed("找不到数据");
                        }
                    } else {
                        //进行数据合并
//                        最终需要的内容 title desc updatetime 旧版本 新版本 package icon
                        for (int i = 0; i < object.size(); i++) {
                            DemoBean net = object.get(i);
                            if (mLocalDemo.contains(net)) {
                                int index = mLocalDemo.indexOf(net);
                                DemoBean local = mLocalDemo.get(index);
                                net.setNewVersionName(local.getVersionName());
                                net.setDesc()
                            }
                        }
                        listener.succeed(object);
                    }
                } else {
                    if (listener != null) {
                        listener.failed(e.getMessage());
                    }
                }
            }
        });
    }

    @Override
    public void saveDemoLocal(Context context, List<DemoBean> list) {

    }
}
