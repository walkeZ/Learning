package com.gdcaihui.luckycoin.android.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gdcaihui.luckycoin.android.R;
import com.gdcaihui.luckycoin.android.widget.ActionBarLayout;

import walke.baselibrary.tools.LayoutParamsUtil;

/**
 * Created by Walke.Z on 2017/4/21.
 * 这是上一层(第二层)封装：
 * ①让子类必须重写几个必要分工明细的方法便于代码编辑提高代码可阅读性
 * ②默认有自定义的标题栏
 * 使用LayoutParams为第二子布局重新配置铺满的宽高，可以有效避免TitleOldActivity的情况
 */
public abstract class ActionBarActivity extends AppActivity implements ActionBarLayout.ActionBarLayoutClickListener,View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        ViewGroup rootView = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.activity_action_bar, null);
//        setContentView(rootLayoutId());
        ViewGroup rootView = (ViewGroup) LayoutInflater.from(this).inflate(rootLayoutId(), null);
        //第一子布局
        ActionBarLayout actionBarLayout = ((ActionBarLayout) findViewById(R.id.aab_titleLayout));
        actionBarLayout.setActionBarLayoutClickListener(this);
        actionBarLayout.setRightId(R.mipmap.tab_scan_transperant2x);
        //添加子类布局为第二子布局,
        View inflate = LayoutInflater.from(this).inflate(abaLayoutId(), null);
        ViewGroup.LayoutParams vg_p_mm = LayoutParamsUtil.getVG_P_MM();
        inflate.setLayoutParams(vg_p_mm);
       /* ViewGroup.LayoutParams layoutParams = inflate.getLayoutParams();
        layoutParams.width=ViewGroup.LayoutParams.MATCH_PARENT;
        layoutParams.height=ViewGroup.LayoutParams.MATCH_PARENT;*/
        rootView.addView(inflate);
        abaInitView(actionBarLayout);
        abaInitData();

    }

    protected abstract int abaLayoutId();

    protected abstract void abaInitView(ActionBarLayout actionBarLayout);

    protected abstract void abaInitData();

    @Override
    protected int rootLayoutId() {
        return R.layout.activity_action_bar;
    }

    @Override
    protected void initialView() {

    }

    @Override
    protected void initialData() {

    }


    @Override
    public void onClick(View view) {

    }

    @Override
    public void leftClick() {
        hideSoft();
        finish();
    }

    @Override
    public void rightClick() {
//        startActivity(new Intent(this, CaptureActivity.class));
//        overridePendingTransition(R.anim.bottom_to_top,R.anim.keep);
    }

}
