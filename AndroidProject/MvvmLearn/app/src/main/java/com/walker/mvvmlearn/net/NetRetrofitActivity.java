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
import com.walker.mvvmlearn.net.model.bean.BannerBean;
import com.walker.mvvmlearn.net.model.bean.BaseBean;
import com.walker.mvvmlearn.net.retrofit2.NetRequest;
import com.walker.mvvmlearn.net.retrofit2.callback.CallBackObserver;
import com.walker.mvvmlearn.net.retrofit2.callback.IResponseCallBack;
import com.walker.mvvmlearn.net.retrofit2.exception.OkHttpException;
import com.walker.mvvmlearn.net.vm.NRViewModel;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

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
//            ApiSubscriber<BaseBean<List<BannerBean>>> apiSubscriber =
//                    new ApiSubscriber<BaseBean<List<BannerBean>>>(NetRetrofitActivity.this, true) {
//                        @Override
//                        public void onSubscribe(Disposable d) {
//                        }
//                        @Override
//                        public void onSuccess(BaseBean<List<BannerBean>> listBaseBean) {
//                        }
//                    };

            toast("getBean ");
            NetRequest.createApi().getBanner()
                    .subscribeOn(Schedulers.io())//指定Observable自身在io线程中执行
                    .observeOn(AndroidSchedulers.mainThread())//指定一个观察者在主线程中国观察这个Observable
                    .subscribe(new CallBackObserver<>(NetRetrofitActivity.this,new IResponseCallBack<BaseBean<List<BannerBean>>>() {
                        @Override
                        public void onSuccess(BaseBean<List<BannerBean>> data) {
                            toast(" getBean onSuccess");
                            nrViewModel.bannerBean.setValue(data);
                        }

                        @Override
                        public void onFail(OkHttpException failure) {
                            toast(" getBean onFail");
                            BaseBean<List<BannerBean>> baseBean = new BaseBean<>();
                            baseBean.setErrorCode(-1);
                            baseBean.setErrorMsg(failure.getEmsg());
                            nrViewModel.bannerBean.setValue(baseBean);
                        }
                    }));//订阅
        }

        public void vmBean() {
            toast("vmBean");
//            nrViewModel.getBanner().observe(NetRetrofitActivity.this, new Observer<BaseBean<List<BannerBean>>>() {
//                @Override
//                public void onChanged(BaseBean<List<BannerBean>> listBaseBean) {
//                    Log.w(TAG, " vmBean onChanged : " + listBaseBean.getData().get(0).toString());
////                    toast(" vmBean onChanged " + listBaseBean.getErrorCode());
//                }
//            });
        }

        public void post(View v) {
            toast("post");
            nrViewModel.getMyBannerObject().observe(NetRetrofitActivity.this, new Observer<Object>() {
                @Override
                public void onChanged(Object o) {
                    Log.i(TAG, "getMyBannerObject onChanged: " + o);
                }
            });


        }
    }
}