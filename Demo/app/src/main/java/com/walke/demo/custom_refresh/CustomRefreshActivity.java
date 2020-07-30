package com.walke.demo.custom_refresh;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.walke.demo.R;

/**
 * Created by walke on 2018/11/8.
 * email:1032458982@qq.com
 * Android 怎么实现支持所有View的通用的下拉刷新控件
 * 参考：https://blog.csdn.net/u010386612/article/details/51372696
 */

public class CustomRefreshActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_refresh);
    }
}
