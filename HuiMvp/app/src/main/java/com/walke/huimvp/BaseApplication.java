package com.walke.huimvp;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by walke.Z on 2018/1/12.
 *
 * https://v.qq.com/x/search/?q=Android+mvp&stag=0&smartbox_ab=     Android开发之MVP架构设计 时间：2017-09-28
 * 参考2：
 * https://www.jianshu.com/p/9d40b298eca9   Android中的MVP模式，带实例
 * 参考3：
 * https://www.jianshu.com/p/14283d8d3a60   MVP google官方demo比较分析
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
