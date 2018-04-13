package com.walke.yatoooondemo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

import com.yatoooon.screenadaptation.ScreenAdapterTools;

/**
 * Created by walke.Z on 2018/4/13.
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(rootLayoutId());
        //在setContentView();后面加上下面这句话
        ScreenAdapterTools.getInstance().loadView((ViewGroup) getWindow().getDecorView());

    }

    protected abstract int rootLayoutId();
}
