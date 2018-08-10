package com.walke.demo.main.views;

import com.walke.demo.BaseIView;

/**
 * Created by walke.Z on 2018/8/10.
 */

public interface MainIView<D extends Object> extends BaseIView {

    void getDataSuccess(D data);


    void getDataFail(Exception exc);
}
