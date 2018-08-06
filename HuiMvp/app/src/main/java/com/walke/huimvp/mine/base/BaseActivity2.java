package com.walke.huimvp.mine.base;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by walke.Z on 2018/8/6.
 *
 * 发现BaseIView中的通用方法showLoadingProgress()、hideLoadingProgress()应该独立新一个IView子接口
 */

//public  class BaseActivity2<P extends BasePresenter1,V extends BaseIView1> extends AppCompatActivity implements BaseIView1 {
public  class BaseActivity2<P extends BasePresenter1,V extends BaseIView1> extends AppCompatActivity implements LoadingIView {


    @Override
    public void showLoadingProgress() {
        Toast.makeText(this,"showLoadingProgress",Toast.LENGTH_LONG).show();
    }

    @Override
    public void hideLoadingProgress() {
        Toast.makeText(this,"hideLoadingProgress",Toast.LENGTH_LONG).show();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
