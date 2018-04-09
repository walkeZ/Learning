package com.walke.huimvp.simple6.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.walke.huimvp.simple5.base.BasePresenter_5;
import com.walke.huimvp.simple5.base.IBaseView_5;

/**
 * Created by walke.Z on 2018/1/9.
 * 抽象->抽象出绑定与解绑操作
 * 注意：为了能够兼容多个模块，兼容多个Activity，所以采用泛型设计
 */

public abstract class BaseFragment<V extends IBaseView_5,P extends BasePresenter_5<V>> extends Fragment {
    private P mPresenter;
    private V mIView;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
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
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter!=null&&mIView!=null){
            mPresenter.detachView();
        }
    }

}
