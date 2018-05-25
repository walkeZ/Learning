package com.walke.realm.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.walke.realm.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by walke.Z on 2018/5/25.
 */

public class QueryActivity extends BaseActivity {
    @BindView(R.id.toolBar)
    Toolbar mToolBar;

    @Override
    protected int rootLayout() {
        return R.layout.activity_query;
    }

    @Override
    protected void initView() {
//        mToolBar.setTitle("查询");
        setToolBar(mToolBar,"查询/改");

    }


    @OnClick({R.id.query_btQueryAll, R.id.query_btQueryByCondition, R.id.query_btQueryOther})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.query_btQueryAll:
                startActivity(new Intent(this,AllStudentActivity.class));
                break;
            case R.id.query_btQueryByCondition:
                break;
            case R.id.query_btQueryOther:
                break;
        }
    }
}
