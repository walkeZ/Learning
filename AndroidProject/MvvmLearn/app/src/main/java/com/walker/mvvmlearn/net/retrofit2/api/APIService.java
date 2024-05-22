package com.walker.mvvmlearn.net.retrofit2.api;


import com.walker.mvvmlearn.net.model.bean.BannerBean;
import com.walker.mvvmlearn.net.model.bean.BaseBean;
import com.walker.mvvmlearn.net.model.bean.LoginBean;

import java.util.List;
import java.util.Map;

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
public interface APIService {
    /**
     * 获取Banner数据
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

}
