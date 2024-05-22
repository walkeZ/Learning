package com.walker.mvvmlearn;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.walker.mvvmlearn.net.retrofit2.IView;

public class BaseActivity extends AppCompatActivity implements IView {

    private boolean mIsOnResume;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mIsOnResume = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        mIsOnResume = false;
    }

    @Override
    public void showLoadDialog() {

    }

    @Override
    public void hideLoadDialog() {

    }

    @Override
    public void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void toLogin() {
        toast("您尚未登录，请您先登录");
    }

    @Override
    public boolean isFinishing() {
        return super.isFinishing();
    }

    @Override
    public boolean isOnPause() {
        return !mIsOnResume;
    }

    @Override
    public boolean isOnResume() {
        return mIsOnResume;
    }
}
