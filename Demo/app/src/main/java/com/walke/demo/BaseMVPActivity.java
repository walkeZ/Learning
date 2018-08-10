package com.walke.demo;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by walke.Z on 2018/8/10.
 * <p>
 * MVP:
 * M-model: 模型、模块(数据层)，包扣但不仅限于数据模型(JavaBean)，可包扣网络模块、dao等
 * V-view: 视图层，即用户交互层：MVP的想法是将大量逻辑从Activity/Fragment中转移到presenter(主持者，代理人)中，
 * 然后View只做与用户相关界面响应即可；P层逻辑处理好后通过IView回调给Activity/Fragment(实现IView接口)
 * Activity/Fragment持有Presenter的对象
 * P-presenter：主持层(逻辑处理层)，持有model层对象调用model逻辑代码(数据获取)，通过持有IView的实现类对象来唤起
 * (如：数据获取结果)回调，
 * 父类封装(泛型)
 */

public abstract class BaseMVPActivity<P extends BasePresenter> extends AppCompatActivity implements BaseIView {

    protected P mPresenter;

    protected Dialog mLoadingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLoadingDialog = new Dialog(this,R.style.custom_dialog);
        mLoadingDialog.setContentView(R.layout.dialog_loading);

    }

    protected void initPresenter(P p) {
        mPresenter = p;
    }

    @Override
    public void showLoading() {
        if (!isFinishing() && mLoadingDialog != null && !mLoadingDialog.isShowing())
            mLoadingDialog.show();

    }

    @Override
    public void hideLoading() {
        if (!isFinishing() && mLoadingDialog != null && mLoadingDialog.isShowing())
            mLoadingDialog.dismiss();
    }
}
