package com.walke.huimvp.simple3;

import com.walke.huimvp.login.model.ILoginModel;
import com.walke.huimvp.simple3.base.BasePresenter_3;

/**
 * Created by walke.Z on 2018/1/8.
 * P层：特点：
 * ①：持有M层引用--直接new
 * ②：持有V层引用--构造器传入
 * ③：对M层和V层进行关联
 */

public class LoginPresenter_3 extends BasePresenter_3 {

    private LoginModel_3  mLoginModel;

    public LoginPresenter_3() {
        mLoginModel = new LoginModel_3();
    }


    public void loggin(String userName,String password){
        mLoginModel.login(userName, password, new ILoginModel() {
            @Override
            public void getDataSuccess() {
                if (getILoginView()!=null) {
                    getILoginView().onLoginResult("data : example:text=登录成功,code=1--LoginPresenter_3");
                }
            }

            @Override
            public void getDataFail() {
                if (getILoginView()!=null)
                    getILoginView().onLoginResult("data : example:text=登录失败,code=-1--LoginPresenter_3") ;
            }
        });
    }

}
