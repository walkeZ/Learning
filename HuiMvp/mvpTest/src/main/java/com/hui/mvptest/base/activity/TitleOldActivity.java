package com.hui.mvptest.base.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hui.mvptest.R;
import com.hui.mvptest.widget.TitleLayout;


/**
 * Created by Walke.Z on 2017/4/21.
 * 这是上一层(第二层)封装
 * ①让子类必须重写几个必要分工明细的方法便于代码编辑提高代码可阅读性
 * ②默认有自定义的标题栏
 * rootView.addView(View.inflate(this,rootLayoutId(),null));
 * 这样直接添加子类布局为第二子布局，在使用某些View时会遇到异常情况
 * 如：PullToRefreshListView
 *
 */
public abstract class TitleOldActivity extends BaseActivity implements TitleLayout.TitleLayoutClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ViewGroup rootView = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.activity_title, null);
        setContentView(rootView);
        //第一子布局
        TitleLayout titleLayout = ((TitleLayout) findViewById(R.id.at_titleLayout));
        titleLayout.setTitleLayoutClickListener(this);
        //添加子类布局为第二子布局
       // rootView.addView(LayoutInflater.from(this).inflate(rootLayoutId(), null));
        rootView.addView(View.inflate(this,rootLayoutId(),null));
        initView(titleLayout);
        initData();
       // initData();

    }

    protected abstract int rootLayoutId();

    protected abstract void initView(TitleLayout titleLayout);

    protected abstract void initData();

    @Override
    public void leftClick() {
        finish();
    }

    @Override
    public void rightClick() {

    }

}
