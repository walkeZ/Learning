package com.walke.huimvp.simple1;

/**
 * Created by walke.Z on 2018/1/8.
 * P层：特点：
 * ①：持有M层引用--直接new
 * ②：持有V层引用--构造器传入
 * ③：对M层和V层进行关联
 */

public class LoginPresenter_1 {

    private LoginModel_1  mLoginModel_1;
    private ILoginView_1 mILoginView_1;

    public LoginPresenter_1(ILoginView_1 iLoginView_1) {
        mILoginView_1 =iLoginView_1;
        mLoginModel_1 = new LoginModel_1();
    }


    public void loggin(String userName,String password){
        mLoginModel_1.login(userName, password, new ILoginModel_1() {
            @Override
            public void getDataSuccess() {
                if (mILoginView_1!=null)
                    mILoginView_1.onLoginResult("data : example:text=登录成功,code=1  LoginPresenter_1");
            }

            @Override
            public void getDataFail() {
                if (mILoginView_1!=null)
                    mILoginView_1.onLoginResult("data : example:text=登录失败,code=-1  LoginPresenter_1");
            }
        });
    }

}
