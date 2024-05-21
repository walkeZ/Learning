package com.walker.mvvmlearn;

import android.app.Application;
import android.content.Context;

import com.walker.mvvmlearn.net.retrofit.HttpsUtils;
import com.walker.mvvmlearn.net.retrofit.RequestInterceptor;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Cache;
import okhttp3.OkHttpClient;


/**
 * @author walker
 * @date 2024/5/20
 * @description Retrofit 学习
 * https://blog.csdn.net/duoduo_11011/article/details/77530947
 */
public class MyApp extends Application {
    public static Context context;
    public static OkHttpClient mOkHttpClient;
    /**
     * 超时时间
     */
    private static final int TIME_OUT = 30;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        initOkHttp();
    }

    private void initOkHttp() {
        //获取缓存路径
        File cacheDir = MyApp.context.getExternalCacheDir();

        //设置缓存的大小
        int cacheSize = 10 * 1024 * 1024;
        //创建我们Client对象的构建者
        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
        okHttpBuilder
                //为构建者设置超时时间
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
//        websocket轮训间隔(单位:秒)
                .pingInterval(20, TimeUnit.SECONDS)
                //设置缓存
                .cache(new Cache(cacheDir.getAbsoluteFile(), cacheSize))
                //允许重定向
                .followRedirects(true)
                //设置拦截器
                .addInterceptor(new RequestInterceptor())
                //添加https支持
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String s, SSLSession sslSession) {
                        return true;
                    }
                });
//                .sslSocketFactory(HttpsUtils.initSSLSocketFactory(), HttpsUtils.initTrustManager()); // 不写也行

        mOkHttpClient = okHttpBuilder.build();

    }

}
