package com.walker.mvvmlearn.net.retrofit2;

public interface IModel {

    String getErrorMsg();   //后台返回的错误信息

    void setErrorMsg(String msg);

    int getErrorCode();
}
