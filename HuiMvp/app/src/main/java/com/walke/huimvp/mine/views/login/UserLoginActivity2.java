package com.walke.huimvp.mine.views.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;

import com.walke.huimvp.R;
import com.walke.huimvp.mine.base.BaseActivity2;
import com.walke.huimvp.mine.models.bean.UserInfo;
import com.walke.huimvp.mine.presenters.LoginPresenter;

/**
 * Created by walke.Z on 2018/8/6.
 *
 * 练习入门是Login案例
 *
 * 发现：父类 BaseActivity1 中实现了BaseIView<P> 这里再实现LoginIView会报错(编译报错)
 * 而对应的LoginIView所特有的loginSuccess、loginFail方法，应该有一个针对性的实现/重写(在UserLoginActivity中)
 * 故父类中应该不需实现BaseIView，只需在子类中实现对应的IView子类即可
 */

//public class UserInfoActivity extends BaseActivity1<UserInfoPresenter> implements LoginIView  {
public class UserLoginActivity2 extends BaseActivity2<LoginPresenter,LoginIView> implements LoginIView {


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
