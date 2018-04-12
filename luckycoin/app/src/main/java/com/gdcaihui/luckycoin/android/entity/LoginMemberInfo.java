package com.gdcaihui.luckycoin.android.entity;

import android.content.Context;

import com.gdcaihui.luckycoin.android.utils.SharepreUtil;


/**
 * @author View
 * @date 2016/12/19 16:47
 */
public class LoginMemberInfo {
    private Context context;
    private final String KEY_ACCOUNT = "account";
    private final String KEY_TOKEN = "token";

    public LoginMemberInfo(Context context) {
        this.context = context;
    }

    public String getAccount() {
        return SharepreUtil.getString(context, KEY_ACCOUNT);
    }

    public void setAccount(String account) {
        SharepreUtil.putString(context, KEY_ACCOUNT, account);
    }

    public String getToken() {
        return SharepreUtil.getString(context, KEY_TOKEN);
    }

    public void setToken(String token) {
        SharepreUtil.putString(context, KEY_TOKEN, token);
    }

}
