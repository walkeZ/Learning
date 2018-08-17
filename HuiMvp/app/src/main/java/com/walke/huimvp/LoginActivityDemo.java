package com.walke.huimvp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.walke.huimvp.login.ui.LoginActivity;
import com.walke.huimvp.simple1.ILoginView_1;
import com.walke.huimvp.simple1.LoginPresenter_1;
import com.walke.huimvp.simple2.ILoginView_2;
import com.walke.huimvp.simple2.LoginPresenter_2;
import com.walke.huimvp.simple3.ILoginView_3;
import com.walke.huimvp.simple3.LoginPresenter_3;
import com.walke.huimvp.simple4.ILoginView_4;
import com.walke.huimvp.simple4.LoginPresenter_4;
import com.walke.huimvp.simple5.ILoginView_5;
import com.walke.huimvp.simple5.LoginPresenter_5;
import com.walke.huimvp.simple5.base.BaseActivity_5;
import com.walke.huimvp.simple6.LoginFragment;
import com.walke.huimvp.utils.ToastUtil;

/**
 * UI层
 */
//public class LoginActivityDemo extends AppCompatActivity implements ILoginView_1,ILoginView_2,ILoginView_3,ILoginView_4 {
public class LoginActivityDemo extends BaseActivity_5<ILoginView_5,LoginPresenter_5> implements ILoginView_1,ILoginView_2,ILoginView_3,ILoginView_4, ILoginView_5 {


    private EditText etName,etPassword;
    private FrameLayout frameLayout;

    private LoginPresenter_1 mLoginPresenter1;
    private LoginPresenter_2 mLoginPresenter2;
    private LoginPresenter_3 mLoginPresenter3;
    private LoginPresenter_4 mLoginPresenter4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logindemo);
        etName = ((EditText) findViewById(R.id.al_username));
        etPassword = ((EditText) findViewById(R.id.al_password));
        frameLayout = ((FrameLayout) findViewById(R.id.al_frameLayout));
        LoginFragment loginFragment = new LoginFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.al_frameLayout,loginFragment).show(loginFragment).commit();
    }

    @Override
    protected LoginPresenter_5 createPresenter() {
        return new LoginPresenter_5();
    }

    @Override
    protected ILoginView_5 createIView() {
        return this;
    }

    public void loginClick(View view) {
//        login1();
//        login2();
//        login3();
//        login4();
//        login5();
        login6();
    }


    /**
     * 第一种：原始方案(普通写法)
     */
    private void login1() {
        // TODO: 2018/1/8 一个登录的网络请求
        //暂时本地测试
        if ("Walke".equals(etName.getText().toString().trim())&&"123456".equals(etPassword.getText().toString().trim())){
            ToastUtil.showMiddleToast(this,"Login Success!");
        }else {
            ToastUtil.showMiddleToast(this,"用户名或者密码出错");
        }

    }

    /**第二种代码结构优化：
     * 团队开发：以上代码不利于分工合作
     * 例如：外包项目(2个月完成->2人开发)-变成->1个月完成->投入4人开发
     * 解决方法：  进行项目框架搭建(分模块：分为UI层、收据层等)
     * 接下来做代码结构优化：->MVP架构设计
     * M层：LoginModel->专门负责数据层->数据库操作、网络操作、文件操作等等...
     * P层：LoginPresenter_1->专门负责交互(中介)->将UI层和数据层进行关联
     * V层：ILoginView_1->进行UI交互(用户操作界面响应)的回调->持有P层引用
     */
    private void login2() {//第一步优化
        mLoginPresenter1=new LoginPresenter_1(this);
        mLoginPresenter1.loggin(etName.getText().toString().trim(),etPassword.getText().toString().trim());
        //you发现问题：当我们的网络请求正在进行，这时候我们退出了Activity，然后UI层引用还在，还会回调，
        //其实已经没有必要了，我们可以选择直接终止请求，即当前代码存在一定的隐患。
        //解决方案：绑定和解绑：
    }

    @Override
    public void onLoginResult(String loginResult) {
        ToastUtil.showMiddleToast(this,"loginResult: "+loginResult);//login2()
        startActivity(new Intent(this, LoginActivity.class));
    }


    private void login3() {//第二步优化
        mLoginPresenter2=new LoginPresenter_2();
        mLoginPresenter2.attachView(this);
        mLoginPresenter2.loggin(etName.getText().toString().trim(),etPassword.getText().toString().trim());
        //发现问题：一个类还好，如果多个类presenter，绑定、解绑都要反复定义？
        //解决方案：将绑定解绑进行抽象(封装)->BasePresenter1
    }

    private void login4() {//第三步优化
        mLoginPresenter3=new LoginPresenter_3();
        mLoginPresenter3.attachView(this);
        mLoginPresenter3.loggin(etName.getText().toString().trim(),etPassword.getText().toString().trim());
        //发现问题：抽象的父类，类型写死了，不易扩展
        //解决方案一：泛型设计
        //解决方案二：强制类型转换也可以(但每一次都要进行转换，很麻烦)
    }

    private void login5() {//第四步优化
        mLoginPresenter4=new LoginPresenter_4();
        mLoginPresenter4.attachView(this);
        mLoginPresenter4.loggin(etName.getText().toString().trim(),etPassword.getText().toString().trim());
        //发现问题：随着Activity、Fragment数量增加，你会发现我们需要反复多次的绑定与解绑，代码冗余了
        //解决方案：Activity的抽象+泛型
    }

    private void login6() {//第四步优化

        getPresenter().login(etName.getText().toString().trim(),etPassword.getText().toString().trim());
        //小拓展：
        getPresenter().thirdLogin("wechat");
        //还需要拓展到fragment--simple6
        //

    }

    @Override //小拓展：
    public void onThirdLoginResult(String registerResult) {
        // TODO: 2018/1/9 扩展其他UI回调， 比如第三方登录；【注册可以在注册界面，再定义一个IRegisterView】
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mLoginPresenter2!=null)
            mLoginPresenter2.detachView();//login3()

        if (mLoginPresenter3!=null)
            mLoginPresenter3.detachView();//login4()
        if (mLoginPresenter4!=null)
            mLoginPresenter4.detachView();//login5()

    }

}
