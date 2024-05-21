package com.walker.mvvmlearn.net;

import android.util.Log;

import com.walker.mvvmlearn.MyApp;
import com.walker.mvvmlearn.net.model.NetResponse;
import com.walker.mvvmlearn.net.model.bean.BannerBean;
import com.walker.mvvmlearn.net.model.bean.BaseBean;
import com.walker.mvvmlearn.net.retrofit.ApiService;
import com.walker.mvvmlearn.net.vm.NRViewModel;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager1 {

    private static String BASE_URL = "https://www.wanandroid.com/";

    private static class Instance {
        private static RetrofitManager1 instance = new RetrofitManager1();
    }

    public static RetrofitManager1 getInstance() {
        return Instance.instance;
    }

    /**
     * 封装之前的简单调用
     */
    public void normalRequest(NRViewModel nrViewModel) {
        // 第一步 创建Retrofit 实例
        Retrofit build = new Retrofit.Builder()
                .baseUrl(BASE_URL) // 添加baseUrl
                .client(MyApp.mOkHttpClient) // 添加OkhttpClient ，不写也也有默认的
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        //第二步：构建接口
        ApiService iApiService = build.create(ApiService.class);

        //第三步：构建被观察者对象
        Observable<String> observable = iApiService.getBanner();

        //第四步：订阅
        observable.subscribeOn(Schedulers.io())//指定Observable自身在io线程中执行
                .observeOn(AndroidSchedulers.mainThread())//指定一个观察者在主线程中国观察这个Observable
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e("TAG", "开始之前");
                    }

                    @Override
                    public void onNext(String response) {
                        Log.e("TAG", "成功" + response);
                        NetResponse<String> value = new NetResponse<>();
                        value.setContent(response);
                        nrViewModel.response.postValue(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("TAG", "失败");
                        String backStr = "{\"data\":[{\"desc\":\"我们支持订阅啦~\",\"id\":30,\"imagePath\":\"https://www.wanandroid.com/blogimgs/42da12d8-de56-4439-b40c-eab66c227a4b.png\",\"isVisible\":1,\"order\":2,\"title\":\"我们支持订阅啦~\",\"type\":0,\"url\":\"https://www.wanandroid.com/blog/show/3352\"},{\"desc\":\"\",\"id\":6,\"imagePath\":\"https://www.wanandroid.com/blogimgs/62c1bd68-b5f3-4a3c-a649-7ca8c7dfabe6.png\",\"isVisible\":1,\"order\":1,\"title\":\"我们新增了一个常用导航Tab~\",\"type\":1,\"url\":\"https://www.wanandroid.com/navi\"},{\"desc\":\"一起来做个App吧\",\"id\":10,\"imagePath\":\"https://www.wanandroid.com/blogimgs/50c115c2-cf6c-4802-aa7b-a4334de444cd.png\",\"isVisible\":1,\"order\":1,\"title\":\"一起来做个App吧\",\"type\":1,\"url\":\"https://www.wanandroid.com/blog/show/2\"}],\"errorCode\":0,\"errorMsg\":\"\"}";
                        NetResponse<String> value = new NetResponse<>();
                        value.setContent(backStr);
                        nrViewModel.response.postValue(value);
                        nrViewModel.responseStr.postValue(backStr);
                        // 30457-30457 TAG     com.walker.mvvmlearn   E  失败22 {"data":[{"desc"
                        Log.e("TAG", "失败22  " + nrViewModel.responseStr.getValue());
                    }

                    @Override
                    public void onComplete() {
                        Log.e("TAG", "结束");
                    }
                });
    }

    /**
     * 封装之前的简单调用
     */
    public void normalRequestBean(NRViewModel nrViewModel) {
        // 第一步 创建Retrofit 实例
        Retrofit build = new Retrofit.Builder()
                .baseUrl(BASE_URL) // 添加baseUrl
                .client(MyApp.mOkHttpClient) // 添加OkhttpClient ，不写也也有默认的
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        //第二步：构建接口
        ApiService iApiService = build.create(ApiService.class);

        //第三步：构建被观察者对象
        Observable<BaseBean<List<BannerBean>>> observable = iApiService.getBannerBean();

        //第四步：订阅
        observable.subscribeOn(Schedulers.io())//指定Observable自身在io线程中执行
                .observeOn(AndroidSchedulers.mainThread())//指定一个观察者在主线程中国观察这个Observable
                .subscribe(new Observer<BaseBean<List<BannerBean>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e("TAG", "normalRequestBean 开始之前 ");
                    }

                    @Override
                    public void onNext(BaseBean<List<BannerBean>> na) {
                        Log.e("TAG", "normalRequestBean 成功 " + na.toString());
                        nrViewModel.bannerBean.setValue(na);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("TAG", "normalRequestBean 失败 " + e.getMessage());
//                        String backStr = "{\"data\":[{\"desc\":\"我们支持订阅啦~\",\"id\":30,\"imagePath\":\"https://www.wanandroid.com/blogimgs/42da12d8-de56-4439-b40c-eab66c227a4b.png\",\"isVisible\":1,\"order\":2,\"title\":\"我们支持订阅啦~\",\"type\":0,\"url\":\"https://www.wanandroid.com/blog/show/3352\"},{\"desc\":\"\",\"id\":6,\"imagePath\":\"https://www.wanandroid.com/blogimgs/62c1bd68-b5f3-4a3c-a649-7ca8c7dfabe6.png\",\"isVisible\":1,\"order\":1,\"title\":\"我们新增了一个常用导航Tab~\",\"type\":1,\"url\":\"https://www.wanandroid.com/navi\"},{\"desc\":\"一起来做个App吧\",\"id\":10,\"imagePath\":\"https://www.wanandroid.com/blogimgs/50c115c2-cf6c-4802-aa7b-a4334de444cd.png\",\"isVisible\":1,\"order\":1,\"title\":\"一起来做个App吧\",\"type\":1,\"url\":\"https://www.wanandroid.com/blog/show/2\"}],\"errorCode\":0,\"errorMsg\":\"\"}";
                        BaseBean<List<BannerBean>> bean = new BaseBean<>();
                        bean.setErrorCode(-1);
                        bean.setErrorMsg("网络请求失败 " + e.getMessage());
                        nrViewModel.bannerBean.setValue(bean);
                        // 30457-30457 TAG     com.walker.mvvmlearn   E  失败22 {"data":[{"desc"
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        Log.e("TAG", "normalRequestBean 结束");
                    }
                });
    }

}
