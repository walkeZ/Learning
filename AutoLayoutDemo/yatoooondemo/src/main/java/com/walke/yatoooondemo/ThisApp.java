package com.walke.yatoooondemo;

import android.app.Application;
import android.content.res.Configuration;

import com.yatoooon.screenadaptation.ScreenAdapterTools;

/**
 * Created by walke.Z on 2018/4/13.
 */

public class ThisApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ScreenAdapterTools.init(this);
    }

    //如果应用屏幕固定了某个方向不旋转的话(比如qq和微信),下面可不写.
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        ScreenAdapterTools.getInstance().reset(this);
    }

}
