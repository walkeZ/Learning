package com.walker.mvvmlearn.net.retrofit2.callback;

import com.walker.mvvmlearn.net.retrofit2.exception.OkHttpException;

/**
 * 描述：自定义回调接口
 */
public interface IResponseCallBack<T> {
    void onSuccess(T data);

    void onFail(OkHttpException failuer);
}
