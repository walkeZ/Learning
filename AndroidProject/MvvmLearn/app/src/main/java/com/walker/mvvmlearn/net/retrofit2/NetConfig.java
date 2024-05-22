package com.walker.mvvmlearn.net.retrofit2;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.OkHttpClient;

/**
 * @author walker
 * @date 2024/5/21
 * @description 配置 域名主地址、okClient等
 * NetNetRequest.getApi(c).get业务
 */
public class NetConfig {

    public static int SUCCESS_CODE = 0; // 接口访问成功的errorCode
    public static final String SPECIAL_PARAM = "xxx"; // 特殊字段，
    public static String BASE_URL = "https://www.wanandroid.com/";

    //
    public static OkHttpClient GetOkHttpClient() {
        //获取缓存路径
//        File cacheDir = MyApplication.context.getExternalCacheDir();
        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
        okHttpBuilder
                //为构建者设置超时时间
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                ////websocket轮训间隔(单位:秒)
                .pingInterval(20, TimeUnit.SECONDS)
                //设置缓存
//                .cache(new Cache(cacheDir.getAbsoluteFile(), cacheSize))
                //允许重定向
                .followRedirects(true)
                //设置拦截器
                .addInterceptor(new ParamInterceptor())
                .addInterceptor(new LogInterceptor())
                //添加https支持
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String s, SSLSession sslSession) {
                        return true;
                    }
                });

        return okHttpBuilder.build();
    }
}
