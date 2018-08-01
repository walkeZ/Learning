package com.hui.mvptest.refresh.linearlayout;

import android.view.View;

import com.hui.mvptest.R;
import com.hui.mvptest.base.activity.AppActivity;

/**
 * Created by walke.Z on 2017/8/8.
 */

public class ScrollActivity2 extends AppActivity {

    @Override
    protected int rootLayoutId() {
        return R.layout.activity_scroll_linearlayout2;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }
    public void click(View v){
        toast("ScrollActivity2");
    }
    public void toasting(View v){
        toast("toasting2");
    }

}
