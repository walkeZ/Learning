package com.walker.mvvmlearn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.walker.mvvmlearn.demo1.Binding1Activity;

/**
 * Mvvm架构学习，
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toDemo1();
    }

    public void toDemo1() {
        /**
         * MVVM 是 Model-View-ViewModel 的简写，是M-V-VM三部分组成。它本质上就是 MVC 的改进版。MVVM 就是将其中的 View 的状态和行为抽象化，
         * 其中 ViewModel 将视图 UI 和业务逻辑分开，它可以取出 Model 的数据同时帮忙处理 View 中由于需要展示内容而涉及的业务逻辑。
         * <p>
         * MVVM采用双向数据绑定，view中数据变化将自动反映到 viewmodel上，反之，model中数据变化也将会自动展示在页面上。把 Model 和 View 关联
         * 起来的就是 ViewModel。ViewModel 负责把 Model 的数据同步到 View 显示出来，还负责把 View 的修改同步回 Model。
         * ——— 原文链接：https://blog.csdn.net/c19344881x/article/details/120135958
         * 首页，
         */
        startActivity(new Intent(this, Binding1Activity.class));
    }

    public void toDemo1(View view) {
        startActivity(new Intent(this, Binding1Activity.class));
    }
}