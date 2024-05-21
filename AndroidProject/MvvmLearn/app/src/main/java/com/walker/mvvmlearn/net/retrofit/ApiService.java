package com.walker.mvvmlearn.net.retrofit;

import com.walker.mvvmlearn.net.model.bean.BannerBean;
import com.walker.mvvmlearn.net.model.bean.BaseBean;

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

    /**
     * 获取Banner数据返回bean
     * @return
     */
    @GET("banner/json")
    Observable<BaseBean<List<BannerBean>>> getBannerBean();
}
