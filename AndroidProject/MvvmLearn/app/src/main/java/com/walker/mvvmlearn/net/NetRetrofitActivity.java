package com.walker.mvvmlearn.net;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.walker.mvvmlearn.BaseActivity;
import com.walker.mvvmlearn.R;
import com.walker.mvvmlearn.databinding.ActivityNetRetrofitBinding;
import com.walker.mvvmlearn.net.vm.NRViewModel;

/**
 * @author walker
 * @date 2024/5/20
 * @description Retrofit 学习
 * https://blog.csdn.net/duoduo_11011/article/details/77530947
 */
public class NetRetrofitActivity extends BaseActivity {
    private static String TAG = "NetRetrofitActivity";
    private ActivityNetRetrofitBinding viewDataBinding;
    private NRViewModel nrViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        nrViewModel = new ViewModelProvider(this, (ViewModelProvider.Factory) new ViewModelProvider.NewInstanceFactory())
                .get(NRViewModel.class);

        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_net_retrofit);

        viewDataBinding.setNrvm(nrViewModel);
        viewDataBinding.setMyClick(new MyClick());
        viewDataBinding.setLifecycleOwner(this); // UI不刷新是因为这个没设置
        nrViewModel.responseStr.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Log.i(TAG, "responseStr onChanged: " + s);
//                toast(s);
            }
        });

//        new Handler().postDelayed(() -> {
//            nrViewModel.responseStr.setValue("new " + nrViewModel.responseStr.getValue());
//            Log.w(TAG, "responseStr onChanged222: " + nrViewModel.responseStr.getValue());
//        }, 3500);
    }

    public class MyClick {
        public void simpleUse() {
//            简单
            toast("simpleUse");
            RetrofitManager1.getInstance().normalRequest(nrViewModel);
        }

        public void simpleBean(View v) {
            toast("get");
            RetrofitManager1.getInstance().normalRequestBean(nrViewModel);
        }

        public void getBean(View v) {
            toast("getBean ");
            nrViewModel.responseStr.setValue("getBean");
        }

        public void post(View v) {
            toast("post");
        }
    }
}