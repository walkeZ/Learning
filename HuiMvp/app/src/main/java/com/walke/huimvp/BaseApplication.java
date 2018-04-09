package com.walke.huimvp;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by walke.Z on 2018/1/12.
 *
 * https://v.qq.com/x/search/?q=Android+mvp&stag=0&smartbox_ab=     Android开发之MVP架构设计 时间：2017-09-28
 */

public class BaseApplication extends Application {



    private RefWatcher mRefWatcher;

    public RefWatcher getRefWatcher() {
        return mRefWatcher;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mRefWatcher = LeakCanary.install(this);
    }


}
