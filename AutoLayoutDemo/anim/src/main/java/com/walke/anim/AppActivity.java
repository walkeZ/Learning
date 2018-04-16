package com.walke.anim;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by walke.Z on 2018/4/16.
 */

public class AppActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onStart() {
        super.onStart();
        ActionBar actionBar = getActionBar();//参考 https://www.jianshu.com/p/550dfc70b6c2
        if (actionBar!=null) {//CoinWalletActivity中  ==null，
            actionBar.setTitle(this.getClass().getSimpleName());
        }
    }
}
