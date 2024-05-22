package com.walker.mvvmlearn.net.vm;

import androidx.lifecycle.MutableLiveData;

import com.walker.mvvmlearn.net.model.bean.BannerBean;
import com.walker.mvvmlearn.net.model.bean.BaseBean;
import com.walker.mvvmlearn.net.retrofit2.NetRequest;
import com.walker.mvvmlearn.net.retrofit2.base.BaseHttpSubscriber;
import com.walker.mvvmlearn.net.retrofit2.base.BaseRepository;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 登录
 */
public class BannerRepository extends BaseRepository {

    /**
     * 登录
     * @param
     * @return
     */
    public MutableLiveData<BaseBean<List<BannerBean>>> getBanner() {

        // 方式①
//        return request(NetRequest.createApi().getBannerData()).get(); // 这是可以的，

        // 方式②失败，未知错误
//        BaseHttpSubscriber<List<BannerBean>> httpSubscriber = new BaseHttpSubscriber<>();
//        NetRequest.createApi().getBannerData().subscribe(httpSubscriber);
//        return httpSubscriber.get();
        // 方式②可以了，对比：少了线程指定
        BaseHttpSubscriber<List<BannerBean>> httpSubscriber = new BaseHttpSubscriber<>();
        NetRequest.createApi().getBannerData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(httpSubscriber);
        return httpSubscriber.get();
    }
}
