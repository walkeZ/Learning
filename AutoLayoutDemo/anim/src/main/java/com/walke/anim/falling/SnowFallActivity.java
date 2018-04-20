package com.walke.anim.falling;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.walke.anim.AppActivity;
import com.walke.anim.R;

/**
 * Created by walke.Z on 2018/4/17.
 * 雪花飘落效果
 */

public class SnowFallActivity extends AppActivity {

    private SnowflakesView mSnowflakesView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snow_fall);

        mSnowflakesView = (SnowflakesView) findViewById(R.id.asf_snowflakesView);

        mSnowflakesView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mSnowflakesView.startSnow();
            }
        }, 1000);

        mSnowflakesView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mSnowflakesView.pauseSnow();
            }
        }, 10000);

        mSnowflakesView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mSnowflakesView.resumeSnow();
            }
        }, 15000);

        mSnowflakesView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mSnowflakesView.stopSnow();
            }
        }, 20000);


    }
}
