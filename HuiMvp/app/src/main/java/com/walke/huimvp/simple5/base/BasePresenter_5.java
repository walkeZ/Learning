package com.walke.huimvp.simple5.base;

/**
 * Created by walke.Z on 2018/1/9.
 */

public abstract class BasePresenter_5<V extends IBaseView_5> {//“ V ” f泛型这个名称可以随意起


    private V mIView;

    public V getIView() {
        return mIView;
    }

    public void attachView(V iView){
        this.mIView =iView;
    }

    public void detachView(){
        this.mIView =null;
        //这是没必要请求了、可以选择终止请求
    }

}
