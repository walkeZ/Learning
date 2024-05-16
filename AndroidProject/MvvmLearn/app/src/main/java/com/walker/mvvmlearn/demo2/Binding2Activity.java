package com.walker.mvvmlearn.demo2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.walker.mvvmlearn.R;
import com.walker.mvvmlearn.databinding.ActivityBinding1Binding;
import com.walker.mvvmlearn.databinding.ActivityBinding2Binding;
import com.walker.mvvmlearn.demo1.Binding1Activity;
import com.walker.mvvmlearn.demo1.User;

public class Binding2Activity extends AppCompatActivity {
    private ActivityBinding2Binding viewDataBinding; // 自动生成与xml的命名有关 activity_binding1
    private MvvmViewModel2 mvvmViewModel2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_binding2);

        // 绑定ViewModel
        mvvmViewModel2 = new MvvmViewModel2(getApplication());
        viewDataBinding.setViewModel2(mvvmViewModel2);
    }
}