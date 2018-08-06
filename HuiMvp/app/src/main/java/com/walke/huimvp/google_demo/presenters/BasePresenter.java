package com.walke.huimvp.google_demo.presenters;

import com.walke.huimvp.google_demo.contract.BaseIView;

/**
 * Created by walke.Z on 2018/8/6.
 *
 *
 *
 */

public interface BasePresenter<V extends BaseIView> {

    void start();// 这里的start()方法就相当于约定了所有的Presenter的初始化操作都放在start()方法中；

}
