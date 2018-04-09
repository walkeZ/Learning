package com.walke.huimvp.simple5;

import com.walke.huimvp.simple5.base.IBaseView_5;

/**
 * Created by walke.Z on 2018/1/8.
 * UI层(activity)的回调响应
 *
 */

public interface ILoginView_5 extends IBaseView_5 {

    void onLoginResult(String loginResult);//loginResult登录后的返回的数据

    void onThirdLoginResult(String registerResult);// //小拓展：第三方登录返回的数据

}
