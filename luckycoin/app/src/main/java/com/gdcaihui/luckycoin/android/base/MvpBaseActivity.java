package com.gdcaihui.luckycoin.android.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by walke.Z on 2018/1/9.
 * 抽象->抽象出绑定与解绑操作
 * 注意：为了能够兼容多个模块，兼容多个Activity，所以采用泛型设计
 */

public abstract class MvpBaseActivity<V extends IBaseView,P extends BasePresenter<V>> extends ActionBarActivity {
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
    }
}
