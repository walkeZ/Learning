package com.walke.realm.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.walke.realm.constants.Config;

import butterknife.ButterKnife;

/**
 * Created by walke.Z on 2018/5/23.
 */

public abstract class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(rootLayout());
        ButterKnife.bind(this);
        initView();
    }

    protected abstract int rootLayout();

    protected abstract void initView();

    protected void setToolBar(Toolbar toolBar, String title) {
        toolBar.setTitle(title);
        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void toast(String message) {
        if (Config.DEBUG||!TextUtils.isEmpty(message))//当项目状态为debug时不做第二个判断
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

}
