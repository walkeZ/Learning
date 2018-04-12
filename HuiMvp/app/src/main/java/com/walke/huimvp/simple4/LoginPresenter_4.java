package com.walke.huimvp.simple4;

import com.walke.huimvp.login.model.ILoginModel;
import com.walke.huimvp.simple4.base.BasePresenter_4;

/**
 * Created by walke.Z on 2018/1/8.
 * P层：特点：
 * ①：持有M层引用--直接new
 * ②：持有V层引用--构造器传入
 * ③：对M层和V层进行关联
 */

public class LoginPresenter_4 extends BasePresenter_4<ILoginView_4> {

    private LoginModel_4  mLoginModel;

    public LoginPresenter_4() {
        mLoginModel = new LoginModel_4();
    }


    public void loggin(String userName,String password){
        mLoginModel.login(userName, password, new ILoginModel() {
            @Override
            public void getDataSuccess() {
                if (getIView()!=null) {
                    getIView().onLoginResult("data : example:text=登录成功,code=1-- LoginPresenter_5");
                }
            }

            @Override
            public void getDataFail() {
                if (getIView()!=null)
                    getIView().onLoginResult("data : example:text=登录失败,code=-1-- LoginPresenter_5") ;
            }
        });
    }

}
