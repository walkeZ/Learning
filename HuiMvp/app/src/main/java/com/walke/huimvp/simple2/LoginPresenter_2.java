package com.walke.huimvp.simple2;

import com.walke.huimvp.simple1.ILoginModel_1;

/**
 * Created by walke.Z on 2018/1/8.
 * P层：特点：
 * ①：持有M层引用--直接new
 * ②：持有V层引用--构造器传入
 * ③：对M层和V层进行关联
 */

public class LoginPresenter_2 {

    private LoginModel_2  mLoginModel;
    private ILoginView_2 mILoginView_2;

    public LoginPresenter_2() {
        mLoginModel = new LoginModel_2();
    }

    public void attachView(ILoginView_2 iLoginView_2){
        this.mILoginView_2=iLoginView_2;
    }

    public void detachView(){
        this.mILoginView_2=null;
        //这是没必要请求了、可以选择终止请求
    }


    public void loggin(String userName,String password){
        mLoginModel.login(userName, password, new ILoginModel_1() {
            @Override
            public void getDataSuccess() {
                if (mILoginView_2!=null)
                    mILoginView_2.onLoginResult("data : example:text=登录成功,code=1  LoginPresenter_2");
            }

            @Override
            public void getDataFail() {
                if (mILoginView_2!=null)
                    mILoginView_2.onLoginResult("data : example:text=登录失败,code=-1  LoginPresenter_2");
            }
        });
    }

}
