package com.walker.mvvmlearn.demo2;

// 自定义回调接口。模拟网络请求的回调接口
public interface MCallback {
    void success(String msg);
    void failed(String err);
}
