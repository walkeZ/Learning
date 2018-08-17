package com.walke.huimvp.google_demo.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.walke.huimvp.R;
import com.walke.huimvp.google_demo.contract.UserInfoContract;
import com.walke.huimvp.google_demo.models.UserInfoModel;
import com.walke.huimvp.google_demo.presenters.UserInfoPresenter;

/**
 * Created by walke.Z on 2018/8/6.
 *
 * 参考： MVP google官方demo比较分析： https://www.jianshu.com/p/14283d8d3a60
 *
 * 发现：父类 BaseActivity1 中实现了BaseIView<P> 这里再实现LoginIView会报错(编译报错)
 * 而对应的LoginIView所特有的loginSuccess、loginFail方法，应该有一个针对性的实现/重写(在UserLoginActivity中)
 * 故父类中应该不需实现BaseIView，只需在子类中实现对应的IView子类即可
 */

//public class UserInfoActivity extends AppCompatActivity implements UserInfoContract.IView {
public class UserInfoActivity extends AppCompatActivity implements UserInfoContract.IView<UserInfoPresenter> {


    private TextView tvInfo;
    private Button btShowInfo;
    private EditText etId;

   /* @Override
    public UserInfoContract.IPresenter getPresenter() {
        return new UserInfoPresenter(this);
    }*/

    @Override
    public UserInfoPresenter getPresenter() {
        return new UserInfoPresenter(this);
    }

    @Override
    public void setPresenter(UserInfoPresenter userInfoPresenter) {

    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo);
        tvInfo = ((TextView) findViewById(R.id.usrinfo_tvInfo));
        btShowInfo = ((Button) findViewById(R.id.usrinfo_btShowInfo));
        etId = ((EditText) findViewById(R.id.usrinfo_etId));
        setPresenter(new UserInfoPresenter(this));
    }


    @Override
    public void showLoading() {
        Toast.makeText(this,"正在加载。。。",Toast.LENGTH_LONG).show();
    }

    @Override
    public void hideLoading() {
        Toast.makeText(this,"加载完成",Toast.LENGTH_LONG).show();
    }

    @Override
    public void showUserInfo(UserInfoModel userInfoModel) {
        tvInfo.setText(userInfoModel.toString());
    }

    @Override
    public String getUserId() {
        return etId.getText().toString().trim();
    }


    public void show(View view) {
//        getPresenter().start();
        getPresenter().loadUserInfo();
        // 发现每次调用都 new 一个Presenter子类，耗时和浪费资源。
        // 尝试优化时发现，Presenter 应该有一个全局变量mPresenter 与 getPresenter和setPresenter对应，
    }
}
