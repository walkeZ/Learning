package com.walke.huimvp.simple2;

import com.walke.huimvp.simple1.ILoginModel_1;

/**
 * Created by walke.Z on 2018/1/8.
 * 数据操作：复杂、耗时
 */

public class LoginModel_2 {

    public void login(String name,String password,ILoginModel_1 iLoginModel1){
        // TODO: 2018/1/8 一个登录的网络请求
        //这里可以随时切换网络框架
        //暂时本地测试
        if ("Walke".equals(name)&&"123456".equals(password)){
//            ToastUtil.showMiddleToast(this,"Login Success!");
            iLoginModel1.getDataSuccess();
        }else {
//            ToastUtil.showMiddleToast(this,"用户名或者密码出错");
            iLoginModel1.getDataFail();
        }
    }
}
