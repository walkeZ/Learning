package com.gdcaihui.luckycoin.android.business.login;

import com.gdcaihui.luckycoin.android.base.BasePresenter;
import com.gdcaihui.luckycoin.android.business.login.model.LoginModel;

/**
 * Created by walke.Z on 2018/1/8.
 * P层：特点：
 * ①：持有M层引用--直接new
 * ②：持有V层引用--构造器传入
 * ③：对M层和V层进行关联
 */

public class LoginPresenter extends BasePresenter<ILoginView> {

    private LoginModel mLoginModel;

    public LoginPresenter() {
        mLoginModel = new LoginModel();
    }


    public void login(String userName, String password){

       /* mLoginModel.login(userName, password, new ILoginModel() {
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
        });*/
    }

    public void thirdLogin(String type){//小拓展
        // TODO: 2018/1/9 第三方登录等其他业务
        /*mLoginModel.thirdLogin(type, new ILoginModel() {
            @Override
            public void getDataSuccess() {
                getIView().onThirdLoginResult("获取数据成功");
            }

            @Override
            public void getDataFail() {
                getIView().onThirdLoginResult("获取数据失败");
            }
        });*/
    }

}
