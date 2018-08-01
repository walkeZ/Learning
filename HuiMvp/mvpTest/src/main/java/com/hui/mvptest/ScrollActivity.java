package com.hui.mvptest;

import android.view.View;

import com.hui.mvptest.base.activity.AppActivity;

/**
 * Created by walke.Z on 2017/8/8.
 */

public class ScrollActivity extends AppActivity {

    @Override
    protected int rootLayoutId() {
        return R.layout.activity_scroll_linearlayout;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }
    public void click(View v){
        toast("ScrollActivity");
    }
    public void toasting(View v){
        toast("toasting");
    }

}
