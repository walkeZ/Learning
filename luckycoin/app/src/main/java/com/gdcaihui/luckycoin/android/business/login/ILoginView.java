package com.gdcaihui.luckycoin.android.business.login;

import com.gdcaihui.luckycoin.android.base.IBaseView;

/**
 * Created by walke.Z on 2018/1/8.
 * UI层(activity)的回调响应
 *
 */

public interface ILoginView extends IBaseView {

    void onLoginResult(String loginResult);//loginResult登录后的返回的数据

    void onThirdLoginResult(String registerResult);// //小拓展：第三方登录返回的数据

}
