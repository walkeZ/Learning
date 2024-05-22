package com.walker.mvvmlearn.net.retrofit2;

public interface IView {

    //    显示菊花
    void showLoadDialog();

    //    取消菊花
    void hideLoadDialog();

    //    显示toast
    void toast(String msg);

    //  未登录的情况下去登录  跳转login
    void toLogin();

    boolean isFinishing();

    boolean isOnResume();

    boolean isOnPause();
}
