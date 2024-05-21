package com.walker.mvvmlearn.net.retrofit;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {

    /**
     * 获取Banner数据
     * @return
     */
    @GET("banner/json")
    Observable<String> getBanner();
}
