package com.walke.realm.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.walke.realm.R;

import butterknife.BindView;
import butterknife.OnClick;

public class RealmActivity extends BaseActivity {

    @BindView(R.id.toolBar)
    Toolbar mToolBar;
    @BindView(R.id.bt_add)
    Button mBtAdd;
    @BindView(R.id.bt_query)
    Button mBtQuery;
    @BindView(R.id.bt_async)
    Button mBtAsync;

    @Override
    protected int rootLayout() {
        return R.layout.activity_realm;
    }

    @Override
    protected void initView() {
        mToolBar.setTitle("Realm首页");
    }

    @OnClick({R.id.bt_add, R.id.bt_query, R.id.bt_async})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_add:
//                Toast.makeText(this,"bt_add",Toast.LENGTH_LONG).show();
                startActivity(new Intent(this,AddActivity.class));
                break;
            case R.id.bt_query:
//                Toast.makeText(this,"bt_query",Toast.LENGTH_LONG).show();
                startActivity(new Intent(this,QueryActivity.class));
                break;
            case R.id.bt_async:
                Toast.makeText(this,"bt_async",Toast.LENGTH_LONG).show();
                break;
        }
    }
}
