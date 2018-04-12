package com.walke.huimvp.simple5;

import com.walke.huimvp.login.model.ILoginModel;

/**
 * Created by walke.Z on 2018/1/8.
 * 数据操作：复杂、耗时
 */

public class LoginModel_5 {

    public void login(String name,String password,ILoginModel iLoginModel){
        // TODO: 2018/1/8 一个登录的网络请求
        //这里可以随时切换网络框架
        //暂时本地测试
        if ("Walke".equals(name)&&"123456".equals(password)){
//            ToastUtil.showMiddleToast(this,"Login Success!");
            iLoginModel.getDataSuccess();
        }else {
//            ToastUtil.showMiddleToast(this,"用户名或者密码出错");
            iLoginModel.getDataFail();
        }
    }
    public void thirdLogin(String type,IThirdLoginModel iThirdLoginModel){ //小拓展：
        // TODO: 2018/1/9 第三方登录具体工作
        if (true){
//            ToastUtil.showMiddleToast(this,"Login Success!");
            iThirdLoginModel.getDataSuccess();
        }else {
//            ToastUtil.showMiddleToast(this,"用户名或者密码出错");
            iThirdLoginModel.getDataFail();
        }
    }
}
