package com.brioal.demomanager.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.brioal.demomanager.bean.DemoBean;
import com.brioal.demomanager.db.DBHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by brioal on 16-11-10.
 * Email : brioal@foxmail.com
 * Github : https://github.com/Brioal
 */

public class DataLoader {
    private static DataLoader mLoader;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static DataLoader getInstance(Context context) {
        if (mLoader == null) {
            mLoader = new DataLoader(context);
        }
        return mLoader;

    }

    private DataLoader(Context context) {
        mContext = context;
        mDatabase = new DBHelper(context, "Demo.db3", null, 1).getWritableDatabase();
    }

    //读取本地存储的Demo列表
    public List<DemoBean> loadDemoDB() {
        List<DemoBean> list = new ArrayList<>();
        Cursor cursor = null;
        try {
            cursor = mDatabase.rawQuery("SELECT * FROM DemoList ", null);
            while (cursor.moveToNext()) {
                DemoBean bean = new DemoBean()
                        .setPackage(cursor.getString(0))
                        .setDesc(cursor.getString(1));
                list.add(bean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    //读取本地所有的Demo
    public List<DemoBean> loadDemoLocal(PackageManager manger) {
        List<DemoBean> list = new ArrayList<>();
        try {
            List<PackageInfo> packages = manger.getInstalledPackages(0);
            for (int i = 0; i < packages.size(); i++) {
                PackageInfo packageInfo = packages.get(i);
                //删选添加
                if (((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) && packageInfo.packageName.contains("brioal")) {
                    DemoBean bean = new DemoBean()
                            .setTitle(packageInfo.applicationInfo.loadLabel(manger).toString())
                            .setPackage(packageInfo.packageName)
                            .setIcon(packageInfo.applicationInfo.loadIcon(manger))
                            .setVersionName(packageInfo.versionName)
                            .setHasUpdate(false)
                            .setLocal(true);
                    list.add(bean);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    //保存数据到本地
    public boolean saveDemoDB(List<DemoBean> list) {
        for (int i = 0; i < list.size(); i++) {
            DemoBean bean = list.get(i);
            //删除数据
            try {
                mDatabase.execSQL("delete from DemoList where mPackage = ?", new Object[]{
                        bean.getPackage()
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
            //保存数据
            try {
                mDatabase.execSQL("insert into DemoList values ( ? , ? )", new Object[]{
                        bean.getPackage(),
                        bean.getDesc(),
                });
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }
}
