package com.walke.greendao.base;

import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Toast;

import com.walke.greendao.GreenDaoApp;

/**
 * Created by walke.Z on 2018/5/23.
 */

public class BaseActivity extends AppCompatActivity {

    public GreenDaoApp getApp(){
        return (GreenDaoApp) getApplication();
    }

    public void toast(String message) {
        if (!TextUtils.isEmpty(message))//当项目状态为debug时不做第二个判断
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

}
