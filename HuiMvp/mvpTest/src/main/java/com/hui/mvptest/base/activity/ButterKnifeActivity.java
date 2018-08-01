package com.hui.mvptest.base.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import butterknife.ButterKnife;

/**
 * Created by walke.Z on 2017/8/8.
 *
 */

public abstract class ButterKnifeActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId());
        ButterKnife.bind(this);
        initData();
    }

    public abstract int layoutId();

    protected abstract void initData();
}
