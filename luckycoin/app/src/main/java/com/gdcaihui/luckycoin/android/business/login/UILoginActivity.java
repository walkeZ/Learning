package com.gdcaihui.luckycoin.android.business.login;

import com.gdcaihui.luckycoin.android.base.MvpBaseActivity;
import com.gdcaihui.luckycoin.android.widget.ActionBarLayout;

/**
 * Created by walke.Z on 2018/1/11.
 */

public class UILoginActivity extends MvpBaseActivity<ILoginView,LoginPresenter> implements ILoginView{
    @Override
    protected int abaLayoutId() {
        return 0;
    }

    @Override
    protected void abaInitView(ActionBarLayout actionBarLayout) {

    }

    @Override
    protected void abaInitData() {

    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected ILoginView createIView() {
        return this;
    }

    @Override
    public void onLoginResult(String loginResult) {

    }

    @Override
    public void onThirdLoginResult(String registerResult) {

    }
}
