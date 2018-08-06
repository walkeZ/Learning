package com.walke.huimvp.mine.base;

/**
 * Created by walke.Z on 2018/8/6.
 */

public interface ModelCallBack<D> {

    void onSuccess(D data);

    void onFail(Exception exc);

}
