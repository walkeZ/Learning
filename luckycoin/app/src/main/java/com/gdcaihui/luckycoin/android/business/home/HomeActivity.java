package com.gdcaihui.luckycoin.android.business.home;

import android.widget.Toast;

import com.gdcaihui.luckycoin.android.R;
import com.gdcaihui.luckycoin.android.base.MvpBaseActivity;
import com.gdcaihui.luckycoin.android.widget.ActionBarLayout;

import walke.baselibrary.ExitApplication;

/**
 * Created by walke.Z on 2018/1/10.
 */

public class HomeActivity extends MvpBaseActivity<IHomeView,HomePresenter> {

    @Override
    protected int abaLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void abaInitView(ActionBarLayout actionBarLayout) {

    }

    @Override
    protected void abaInitData() {

    }

    @Override
    protected int rootLayoutId() {
        return 0;
    }


    @Override
    protected HomePresenter createPresenter() {
        return null;
    }

    @Override
    protected IHomeView createIView() {
        return null;
    }


    private long mPreTime;
    @Override
    public void onBackPressed() {
        if (this instanceof HomeActivity) {
            if (System.currentTimeMillis() - mPreTime > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次,退出应用", Toast.LENGTH_SHORT).show();
                mPreTime = System.currentTimeMillis();
                //getLucyCoinApplication().exit();
                return;
            }
            //getLucyCoinApplication().exit();
            ExitApplication.getInstance().exit();
        }
        super.onBackPressed();
    }
}
