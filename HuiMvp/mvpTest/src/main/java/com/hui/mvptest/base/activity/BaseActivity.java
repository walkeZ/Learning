package com.hui.mvptest.base.activity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;

import com.hui.mvptest.TestApp;
import com.hui.mvptest.util.LogUtil;
import com.hui.mvptest.util.ToastUtil;

/**
 * Created by walke.Z on 2017/8/4.
 */

public class BaseActivity extends AppCompatActivity {


    public TestApp getTestApp() {
        return TestApp.getInstance();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //沉浸式状态栏
        //StatusBarCompat.compat(this, Color.RED);
        //hideVirtualButtons();

        initState();
    }

    /**调用该方法后，虚拟案件就会被隐藏 从屏幕底部向上拉，可以再次显示
     * 与布局xml文件 以下两个属性连用[而6.0异常]
     * android:clipToPadding="true"
     * android:fitsSystemWindows="true"
     * 沉浸式导航栏(虚拟按键栏)会导致上下边界异常
     */
    @SuppressLint("NewApi")
    private void hideVirtualButtons() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_IMMERSIVE);
        }
    }

    /**
     * 在需要实现沉浸式状态栏的Activity的布局中添加以下参数
     * android:fitsSystemWindows="true"
     * android:clipToPadding="true"
     */
    private void initState() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 只沉浸式状态栏，与布局xml文件 以下两个属性连用[而6.0也正常]
            // android:clipToPadding="true"
            // android:fitsSystemWindows="true"

            //透明导航栏
            //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    protected void toast(String message){
        if (!TextUtils.isEmpty(message))
            ToastUtil.showToast(this,message);
    }
    protected void middleToast(String message){
        if (!TextUtils.isEmpty(message))
            ToastUtil.showMidlleToast(this,message);
    }
    protected void toastTime(String message,int time){
        if (!TextUtils.isEmpty(message))
            ToastUtil.showToastWithTime(this,message,time);
    }
    protected void logI(String message){
        if (!TextUtils.isEmpty(message))
            LogUtil.i(this.getClass().getSimpleName(),"-------------------> "+message);
    }
    protected void logD(String message){
        if (!TextUtils.isEmpty(message))
            LogUtil.d(this.getClass().getSimpleName(),"--------> "+message);
    }
    protected void logE(String message){
        if (!TextUtils.isEmpty(message))
            LogUtil.e(this.getClass().getSimpleName(),"---------      -----------> "+message);
    }

}
