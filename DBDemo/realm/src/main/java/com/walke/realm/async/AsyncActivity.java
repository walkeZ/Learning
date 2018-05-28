package com.walke.realm.async;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.walke.realm.R;
import com.walke.realm.activity.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by walke.Z on 2018/5/28.
 */

public class AsyncActivity extends BaseActivity {
    @BindView(R.id.toolBar)
    Toolbar mToolBar;
    @BindView(R.id.aa_btAddDelete)
    Button mBtAddDelete;
    @BindView(R.id.aa_btUpdateQuery)
    Button mBtUpdateQuery;

    @Override
    protected int rootLayout() {
        return R.layout.activity_async;
    }

    @Override
    protected void initView() {
        setToolBar(mToolBar,"异步操作");
    }

    @OnClick({R.id.aa_btAddDelete, R.id.aa_btUpdateQuery})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.aa_btAddDelete:
                startActivity(new Intent(this,AddDeleteActivity.class));
                break;
            case R.id.aa_btUpdateQuery:
                startActivity(new Intent(this,AsyncQueryActivity.class));
                break;
        }
    }
}
