package com.walke.autolayoutdemo;

import android.app.Application;

import com.zhy.autolayout.config.AutoLayoutConifg;

/**
 * Created by walke.Z on 2018/4/13.
 *
 * https://github.com/hongyangAndroid/AndroidAutoLayout
 */

public class ThisApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //默认使用的高度是设备的可用高度，也就是不包括状态栏和底部的操作栏的，
        // 如果你希望拿设备的物理高度进行百分比化,可以在Application的onCreate方法中进行设置:
        AutoLayoutConifg.getInstance().useDeviceSize();
    }
}
