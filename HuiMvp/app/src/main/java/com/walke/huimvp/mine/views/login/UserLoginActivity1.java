package com.walke.huimvp.mine.views.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;

import com.walke.huimvp.R;
import com.walke.huimvp.mine.base.BaseActivity1;
import com.walke.huimvp.mine.models.bean.UserInfo;
import com.walke.huimvp.mine.presenters.LoginPresenter;

/**
 * Created by walke.Z on 2018/8/6.
 *
 * 发现：父类 BaseActivity1 中实现了BaseIView<P> 这里再实现LoginIView会报错(编译报错)
 *
 */

public class UserLoginActivity1 extends BaseActivity1<LoginPresenter,LoginIView> implements LoginIView {

    private EditText etName,etPassword;
    private LoginPresenter mLoginPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etName = ((EditText) findViewById(R.id.login_username));
        etPassword = ((EditText) findViewById(R.id.login_password));
        mLoginPresenter = new LoginPresenter(this);
    }

    public void login(View v){
        String name = etName.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        mLoginPresenter.login(name,password);
    }

    @Override
    public void loginSuccess(UserInfo userInfo) {
        etName.setText(userInfo.toString());
    }

    @Override
    public void loginFail(Exception exc) {
        etName.setText(exc.getMessage());
    }
}
