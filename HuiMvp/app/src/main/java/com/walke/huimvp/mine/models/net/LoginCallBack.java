package com.walke.huimvp.mine.models.net;

import com.walke.huimvp.mine.models.bean.UserInfo;

/**
 * Created by walke.Z on 2018/8/6.
 */

public interface LoginCallBack {

    void onSuccess(UserInfo userInfo);
    void onFail(Exception exc);

}
