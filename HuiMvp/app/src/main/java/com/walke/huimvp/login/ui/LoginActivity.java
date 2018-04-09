package com.walke.huimvp.login.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.walke.huimvp.R;
import com.walke.huimvp.simple5.base.BaseActivity_5;
import com.walke.huimvp.simple5.base.BasePresenter_5;
import com.walke.huimvp.simple5.base.IBaseView_5;

public class LoginActivity extends BaseActivity_5 {

    private EditText etName,etPassword;

    final Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.e("eee", "message");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etName = ((EditText) findViewById(R.id.login_username));
        etPassword = ((EditText) findViewById(R.id.login_password));
        handler.sendEmptyMessageDelayed(0, 30000);
    }

    @Override
    protected BasePresenter_5 createPresenter() {
        return null;
    }

    @Override
    protected IBaseView_5 createIView() {
        return null;
    }

    //
    public void login(View view) {

    }

}
