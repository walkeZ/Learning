package com.walke.huimvp.simple5.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.walke.huimvp.BaseApplication;

/**
 * Created by walke.Z on 2018/1/9.
 * 抽象->抽象出绑定与解绑操作
 * 注意：为了能够兼容多个模块，兼容多个Activity，所以采用泛型设计
 */

public abstract class BaseActivity_5<V extends IBaseView_5,P extends BasePresenter_5<V>> extends AppCompatActivity {
    private P mPresenter;
    private V mIView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mPresenter==null){
            mPresenter = createPresenter();
        }
        if (mIView==null){
            mIView=createIView();
        }

        if (mPresenter!=null&&mIView!=null){
            mPresenter.attachView(mIView);
        }
    }

    public P getPresenter() {
        return mPresenter;
    }

    protected abstract P createPresenter();

    protected abstract V createIView();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter!=null&&mIView!=null){
            mPresenter.detachView();
        }
        ((BaseApplication) getApplication()).getRefWatcher().watch(this);

    }
}
