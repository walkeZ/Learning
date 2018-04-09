package com.walke.huimvp.simple4;

import com.walke.huimvp.simple4.base.IBaseView_4;

/**
 * Created by walke.Z on 2018/1/8.
 * UI层(activity)的回调响应
 *
 */

public interface ILoginView_4 extends IBaseView_4 {

    void onLoginResult(String loginResult);//loginResult登录后的返回的数据

}
