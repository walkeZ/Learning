package com.walke.huimvp.mine.presenters;

import com.walke.huimvp.mine.base.BasePresenter1;
import com.walke.huimvp.mine.models.bean.UserInfo;
import com.walke.huimvp.mine.models.net.HttpUtils;
import com.walke.huimvp.mine.models.net.LoginCallBack;
import com.walke.huimvp.mine.views.login.LoginIView;

;

/**
 * Created by walke.Z on 2018/8/6.
 * presenter 的主要责任是 做model层和View层的桥梁
 * 一般来说，presenter 会对应持有 相应逻辑模块的IView实现类(子类)对象【目的是将业务逻辑执行结果回调给View层】
 * 和持有相应逻辑模块的IModel的实现类(子类)对象，目的是用于执行业务逻辑。
 *
 */

public class LoginPresenter extends BasePresenter1<LoginIView> {


    public LoginPresenter(LoginIView iView) {
        super(iView);
    }


    public void login(String name,String psw){
        // 调用model中的网络封装模块实现请求
        // 这里也可以是持有model的引用(对象)，然后执行业务逻辑：登录
        getIVIew().showLoading();
        HttpUtils.login(name, psw, new LoginCallBack() {
            @Override
            public void onSuccess(UserInfo userInfo) {
                LoginIView ivIew = getIVIew();
                if (ivIew!=null) {
                    getIVIew().showLoading();
                    ivIew.loginSuccess(userInfo);
                }

            }

            @Override
            public void onFail(Exception exc) {
                if (getIVIew()!=null) {
                    getIVIew().hideLoading();
                    getIVIew().loginFail(exc);
                }

            }
        });
    }

}
