package com.walke.huimvp.google_demo.contract;

import com.walke.huimvp.google_demo.models.UserInfoModel;
import com.walke.huimvp.google_demo.presenters.BasePresenter;

/**
 * Created by walke.Z on 2018/8/6.
 *
 * 再来看契约接口：将IPresenter 与 IView 关联起来
 *
 */

public interface UserInfoContract {

    interface IPresenter extends BasePresenter{
        void loadUserInfo(); // 假设接口请求需要一个用户Id
    }

//    interface IView<> extends BaseIView1<IPresenter>{
    interface IView<P extends IPresenter> extends BaseIView<P>{

    @Override
    P getPresenter();

    @Override
    void setPresenter(P p);

    void showLoading(); // 显示加载框
        void hideLoading(); // 隐藏加载框
        void showUserInfo(UserInfoModel userInfoModel); // 将网络请求到的用户信息回调给View层
        String getUserId();// 假设接口请求需要一个id
    }

}
