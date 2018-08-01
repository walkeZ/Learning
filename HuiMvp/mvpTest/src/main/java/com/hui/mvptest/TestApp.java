package com.hui.mvptest;

import android.app.Application;

/**
 * Created by walke.Z on 2017/8/15.
 */

public class TestApp extends Application {

    private static TestApp instance;
    public synchronized static TestApp getInstance() {
        if (null == instance)
            instance = new TestApp();
        return instance;
    }

    private boolean isWebViewTop;

    public boolean isWebViewTop() {
        return isWebViewTop;
    }

    public void setWebViewTop(boolean webViewTop) {
        isWebViewTop = webViewTop;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
