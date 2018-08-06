package com.walke.huimvp.google_demo.presenters;

import android.os.Handler;
import android.text.TextUtils;

import com.walke.huimvp.google_demo.contract.UserInfoContract;
import com.walke.huimvp.google_demo.models.UserInfoModel;

/**
 * Created by walke.Z on 2018/8/6.
 *
 * 1：UserInfoPresenter 构造函数中传入UserInfoContract.View，并且调用view的setPresenter()方法；
 * 2：将所有的初始化操作都放在start()方法中（这里demo只有一个：网络请求获取用户信息），
 *    这样只要进入界面的时候调用start()方法就可以执行一系列初始化的操作，这就相当于一种约定好的开发。
 */

public class UserInfoPresenter implements UserInfoContract.IPresenter {

    private UserInfoContract.IView mIView;

    /** 参考： java--依赖、关联、聚合和组合之间区别的理解
     *      https://www.cnblogs.com/wanghuaijun/p/5421419.html
     *  关联关系 ：在java中一般使用成员变量来实现
     * @param iView
     */
    public UserInfoPresenter(UserInfoContract.IView iView) { // 构造器传入引用(对象)，这是
        mIView = iView;
    }

    @Override
    public void start() {
        loadUserInfo();
    }

    @Override
    public void loadUserInfo() {
        // 模拟请求1： 开始1:先显示进度，
        mIView.showLoading();
        // 模拟网络请求，开始2:设置参数等
        String userId = mIView.getUserId();
        if (!TextUtils.isEmpty(userId)) {
            loadUserInfo(userId);
        }else {
            // 模拟网络请求，延时
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    // 模拟网络请求，结束(成功)
                    mIView.hideLoading();
                    mIView.showUserInfo(new UserInfoModel("walke1 ", "123456", 26));
                }
            }, 2500);
        }
    }


    private void loadUserInfo(String userId) {
        // 模拟请求2： 开始先显示进度，
        mIView.showLoading();
        // 模拟网络请求，延时
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 模拟网络请求，结束(成功)
                mIView.hideLoading();
                mIView.showUserInfo(new UserInfoModel("walke2 ","123456",26));
            }
        },2500);
    }
}
