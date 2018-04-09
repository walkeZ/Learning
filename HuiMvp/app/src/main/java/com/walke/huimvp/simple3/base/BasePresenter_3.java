package com.walke.huimvp.simple3.base;

import com.walke.huimvp.simple3.ILoginView_3;

/**
 * Created by walke.Z on 2018/1/9.
 */

public abstract class BasePresenter_3 {


    private ILoginView_3 mILoginView;

    public ILoginView_3 getILoginView() {
        return mILoginView;
    }

    public void attachView(ILoginView_3 iLoginView_3){
        this.mILoginView=iLoginView_3;
    }

    public void detachView(){
        this.mILoginView=null;
        //这是没必要请求了、可以选择终止请求
    }

}
