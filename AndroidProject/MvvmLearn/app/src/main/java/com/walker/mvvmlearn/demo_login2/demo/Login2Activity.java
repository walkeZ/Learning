package com.walker.mvvmlearn.demo_login2.demo;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;

import com.walker.mvvmlearn.BaseActivity;
import com.walker.mvvmlearn.R;
import com.walker.mvvmlearn.databinding.ActivityLogin2Binding;
import com.walker.mvvmlearn.demo_login2.demo.data.entity.Login2Response;
import com.walker.mvvmlearn.demo_login2.demo.vm.Login2ViewModel;
import com.walker.mvvmlearn.demo_login2.demo.vm.RequestLogin2ViewModel;

/**
 * @author walker
 * @date 2024/5/15
 * @description Mvvm架构学习
 * https://www.bilibili.com/video/BV13Y4y1H7FG?p=4&vd_source=412650ca810562b4df78e0e3fa9484f6
 */
public class Login2Activity extends BaseActivity {

    ActivityLogin2Binding login2Binding; // 这个就是布局文件，与xml命名关联
    Login2ViewModel login2ViewModel; // 状态的VM
    private RequestLogin2ViewModel reqLogin2ViewModel;
    private ProgressDialog mProgressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        login2ViewModel = new ViewModelProvider(this, (ViewModelProvider.Factory) new ViewModelProvider.NewInstanceFactory())
                .get(Login2ViewModel.class);

        reqLogin2ViewModel = new ViewModelProvider(this, (ViewModelProvider.Factory) new ViewModelProvider.NewInstanceFactory())
                .get(RequestLogin2ViewModel.class);

        // 布局丢给了DataBinding 建立DataBinding的绑定
        login2Binding = DataBindingUtil.setContentView(this, R.layout.activity_login2);
        login2Binding.setVm(login2ViewModel);
        login2Binding.setMyClick(new MyClick());
        login2Binding.setLifecycleOwner(this);

        login2ViewModel.loginState.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (mProgressDialog == null || !mProgressDialog.isShowing()) return;
                mProgressDialog.dismiss();
                mProgressDialog = null;
            }
        });

        reqLogin2ViewModel.loginFileData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                login2ViewModel.loginState.setValue(s);
            }
        });

        reqLogin2ViewModel.loginSuccessData.observe(this, new Observer<Login2Response>() {
            @Override
            public void onChanged(Login2Response login2Response) {
                login2ViewModel.loginState.setValue(login2Response.getMsg());
            }
        });

    }

    // 处理布局所有的点击事件
    // 以内部类的方式实现，是为了更好的获取程序上下文环境，Activity
    public class MyClick {
        // 点击登录的事件
        public void login() {
            toast("login");
            // 从状态VM中获取状态，我只要面向状态的VM，就能获取布局的所有一切数据(按需在login2ViewModel中定义并于xml绑定)
            if (login2ViewModel.userName.getValue() == null || login2ViewModel.userPwd.getValue() == null) {
                login2ViewModel.loginState.setValue("用户名 或 密码 为空，请检查一下");
                return;
            }

            mProgressDialog = new ProgressDialog(Login2Activity.this);
            mProgressDialog.setTitle(login2ViewModel.userName.getValue() + " 用户，正在登录中...");
            mProgressDialog.show();

            // 请求专用的VM
            reqLogin2ViewModel.requestLogin(login2ViewModel.userName.getValue(), login2ViewModel.userPwd.getValue());
        }

        public void clearUaeName(View view) {
            toast("clearUaeName");
        }
    }
}