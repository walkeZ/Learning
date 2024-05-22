package com.walker.mvvmlearn.net.vm;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.walker.mvvmlearn.net.model.bean.BannerBean;
import com.walker.mvvmlearn.net.model.bean.BaseBean;
import com.walker.mvvmlearn.net.retrofit2.NetRequest;
import com.walker.mvvmlearn.net.retrofit2.base.BaseRepository;

import java.util.List;

/**
 * 登录
 */
public class LoginRepository extends BaseRepository {

    /**
     * 登录
     * @param
     * @return
     */
    public MutableLiveData<BaseBean<List<BannerBean>>> getBanner() {
        return request(NetRequest.createApi().getBannerData()).get();
    }
}
