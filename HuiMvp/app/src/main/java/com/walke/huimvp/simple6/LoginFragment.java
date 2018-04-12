package com.walke.huimvp.simple6;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.walke.huimvp.R;
import com.walke.huimvp.simple5.ILoginView_5;
import com.walke.huimvp.simple5.LoginPresenter_5;
import com.walke.huimvp.simple6.base.BaseFragment;
import com.walke.huimvp.utils.ToastUtil;

/**
 * Created by walke.Z on 2018/1/9.
 */

//public class LoginFragment extends Fragment implements ILoginView_5 {
public class LoginFragment extends BaseFragment<ILoginView_5,LoginPresenter_5> implements ILoginView_5 {

    private EditText etUasername,etPassword;
//    private LoginPresenter_5 mLoginPresenter_5;

    @Override
    protected LoginPresenter_5 createPresenter() {
        return new LoginPresenter_5();
    }

    @Override
    protected ILoginView_5 createIView() {
        return this;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login,null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        etUasername = ((EditText) view.findViewById(R.id.fl_etUsername));
        etPassword = view.findViewById(R.id.fl_etPassword);
//        mLoginPresenter_5 = new LoginPresenter_5();
//        mLoginPresenter_5.attachView(LoginFragment.this);
        view.findViewById(R.id.fl_btLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mLoginPresenter_5.login(etUasername.getText().toString().trim(),etPassword.getText().toString().trim());
                getPresenter().login(etUasername.getText().toString().trim(),etPassword.getText().toString().trim());
            }
        });
    }

    @Override
    public void onLoginResult(String loginResult) {
        ToastUtil.showMiddleToast(getContext(),"LoginFragment-->loginResult: "+loginResult);
    }

    @Override
    public void onThirdLoginResult(String registerResult) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        mLoginPresenter_5.detachView();
    }
}
