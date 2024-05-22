package com.walker.mvvmlearn.net.retrofit2.api;


import com.walker.mvvmlearn.net.model.bean.BannerBean;
import com.walker.mvvmlearn.net.model.bean.BaseBean;
import com.walker.mvvmlearn.net.model.bean.LoginBean;
import com.walker.mvvmlearn.net.retrofit2.base.vo.VoBase;

import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created on 2021/12/28 14:17
 *
 * @author Gong Youqiang
 */
public interface APIService2 {
    /**
     * 获取Banner数据
     *
     * @return
     */
    @GET("banner/json")
    Observable<BaseBean<List<BannerBean>>> getBanner();

    /**
     * 登录
     */
    @POST("user/login")
    @FormUrlEncoded
    Observable<BaseBean<LoginBean>> postLogin(@FieldMap Map<String, String> map);

    /**
     * 获取Banner数据返回bean
     *
     * @return
     */
    @GET("banner/json")
    Flowable<BaseBean<List<BannerBean>>> getBannerData();

    /**
     * 获取Banner数据
     *
     * @return
     */
    @GET("banner/json")
    Observable<VoBase<List<BannerBean>>> getMyBanner();

    /**
     * 获取Banner数据
     *
     * @return
     */
    @GET("banner/json")
    Flowable<Object> getBannerObject(); // Object接口的泛型要记得改
}
