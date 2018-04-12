package com.walke.huimvp.location;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.walke.huimvp.R;
import com.walke.huimvp.utils.SDCardUtil;

/**
 * Created by walke.Z on 2018/1/16.
 */

public class MainActivity extends AppCompatActivity {

    private final String TAG = MainActivity.this.getClass().getSimpleName();
    public static TextView text;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkSdCard();
        text= (TextView) findViewById(R.id.main_TextView);
        FrameLayout frameLayout = ((FrameLayout) findViewById(R.id.main_FrameLayout));
        TupianFragment tupianFragment = new TupianFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.main_FrameLayout,tupianFragment).show(tupianFragment).commit();
        checkSdCard();
    }
    private void checkSdCard() {
        String totalSize = SDCardUtil.getTotalSize(this);
        String availableSize = SDCardUtil.getAvailableSize(this);
        Log.i(TAG,"sd卡内存：totalSize="+totalSize+"----availableSize="+availableSize);
    }


}
