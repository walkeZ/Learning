package com.walker.mvvmlearn.demo_login2;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import com.walker.mvvmlearn.BaseActivity;
import com.walker.mvvmlearn.R;
import com.walker.mvvmlearn.databinding.ActivityLogin21Binding;

/**
 * 源于：https://www.bilibili.com/video/BV13Y4y1H7FG/?p=4&vd_source=412650ca810562b4df78e0e3fa9484f6
 * <p> 一、历史
 * 架构发展简要：
 * 2011年 ，MVC 天然的架构模式，原因是Android项目天然就是图与逻辑分开的结构。
 * Main文件中分出2大类（1、java即逻辑代码包；2、res资源包(图片、xml(字串、color、view、layout)等)）
 * > Activity（controller） + xml（view） + entity（Model）
 * > Activity（窗口，主要承接逻辑/交互含网络请求 等即controller）Activity臃肿，还包括修改UI及UI交互响应，网络请求等(VIEW,controller，data)，做到东西太多，违背单一职责原则。MVC模式本身没有问题，主要是在Android上不纯粹，不契合,Activity失控了。
 * > Xml（布局视图，Layout\view等 即：view）
 * > entity（数据实体对象，网络请求返回解析，在Activity中与view关联，如User有头像、名称登录后从网络返回然后显示到界面 即：Model）
 *
 * 2015~18 MVP
 * > V：Activity 是View层了。仅负责更新UI，响应UI交互（用户点击xml->activity->presenter->逻辑完了->activity->xml）
 * > P：P层承接逻辑层，承接了网络请求，交互响应内容等逻辑业务，最后出现接口越来越多，即接口地域的问题。EventBus减轻了一定的接口两
 * > M: 还是数据实体变化不大，还是数据实体层(对象)
 *
 * 2019 ~ 今天 MVVM 首先，四个字母3个绝角色
 * > V:XMl、Activity 还是View层，引入了datBinding库，减少了大量的设置UI代码，由datBinding代工实现了。【弊端项目大了，dataBinding会生长大量环境包代工代码会影响一定的性能】后面Google对MVVM升级推出更优一点的架构框架（MVVM + JetPack全家桶）
 * > VM: 代替了P层，引入了一个全新库datBinding，可以直接联通V(xml)与M（数据），ViewModel是官方类(工具)
 * > M:还是数据实体变化不大，还是数据实体层(对象)
 * >
 *
 * 架构选型：
 * >> MVC  1.灵活小项目（结构简单明了）Activity的任务不会过重，能清晰实现；2 个人开发，原因该模式：解耦低，容易有交叉冲突。
 * >> MVP  1.大型项目 意味着业务多->接口多(接口地狱)->需要人多。2.业务重-P层重的
 * >> MVVM  1.UI经常性改变(如直播、社区)视图画面更新频繁的(如点赞、收藏、评论等待多)的
 */
public class Login21Activity extends BaseActivity {
    private static String TAG_LOGIN2 = "TAG_LOGIN21";
    private ActivityLogin21Binding binding;
    private Login21ViewModel vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login2);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login21);

        // 布局丢给了DataBinding，建立DataBinding的绑定
        binding.setLm(new Login21Model());
         // new LoginViewModel()
        vm = new ViewModelProvider(this).get(Login21ViewModel.class);
        binding.setVm(vm);
        binding.setMyClick(new MyClick());
        binding.setLifecycleOwner(this);
        //

        binding.getVm().password.observe(this, (text)->{
            Log.i(TAG_LOGIN2, "onCreate: " + vm.isInputNotEmpty());
            vm.isInputPassword.setValue(!TextUtils.isEmpty(text));
        });

        learningLiveData();
    }


    public class MyClick {
        public void click() {
            Log.i(TAG_LOGIN2, "click: vm.username " + vm.username + ", " + vm.password.getValue());
            Login21Activity.this.toast("MyClick");
        }
        public void setting() {
            vm.username = "李四";
            vm.password.setValue("666");
            Login21Activity.this.toast("setting");
        }
    }

    private void learningLiveData() {

        TextView tvLiveData = findViewById(R.id.login21_tvLeanLiveData);
        tvLiveData.setOnClickListener(v-> startActivity(new Intent(this, ViewModel2Activity.class)));

        // liveData重点，①观察数据变化-更新UI。②按业务场景更新数据。有点与生命周期绑定，不可见是不会回调不会触发监听，即不可见时不会刷UI

        //LiveData 能够检测 Activity、Fragment生命周期的状态，如果Activity被关闭了，LiveData不会做事
        // 可追逐性很强
        // 只做一件事，观察/监听 数据变化
        LearningLiveData.getInstance().getInfo().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Log.i(TAG_LOGIN2, "onChanged: " + s);
                // 把观察到的数据，设置给textView
                tvLiveData.setText(s);
            }
        });
        // 触发
        new Thread(() -> {
            try {
                Thread.sleep(5000);
//                LearningLiveData.getInstance().getInfo().setValue("线程5s后的数据");
                // java.lang.IllegalStateException: Cannot invoke setValue on a background thread
                // 所以也要注意子线程不能修改UI
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

        tvLiveData.postDelayed(()->{
            LearningLiveData.getInstance().getInfo().setValue("线程2.5s后的Hello");
        },2500);
    }
}