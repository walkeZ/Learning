package com.walke.huimvp.mine.views.login;

import com.walke.huimvp.mine.base.BaseIView1;
import com.walke.huimvp.mine.models.bean.UserInfo;

/**
 * Created by walke.Z on 2018/8/6.
 */

public interface LoginIView extends BaseIView1 {

    void loginSuccess(UserInfo userInfo);

    void loginFail(Exception exc);

}
