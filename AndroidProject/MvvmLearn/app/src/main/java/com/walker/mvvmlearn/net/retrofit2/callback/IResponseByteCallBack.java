package com.walker.mvvmlearn.net.retrofit2.callback;

import java.io.File;

/**
 * 流的回调接口
 */
public interface IResponseByteCallBack {
    //请求成功回调事件处理
    void onSuccess(File file);

    //请求失败回调事件处理
    void onFailure(String failureMsg);
}
