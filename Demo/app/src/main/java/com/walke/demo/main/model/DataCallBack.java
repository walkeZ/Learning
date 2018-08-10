package com.walke.demo.main.model;

/**
 * Created by walke.Z on 2018/8/10.
 */

public interface DataCallBack<D extends Object> {

    void onSuccess(D data);

    void onFail(Exception exc);

}
