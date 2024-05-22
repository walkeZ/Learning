package com.walker.mvvmlearn.net.retrofit2.base;


import com.walker.mvvmlearn.net.model.bean.BaseBean;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Repository基类
 *
 * @author twilight
 * @Time 2019-07-21
 */

public class BaseRepository {

    /**
     * 请求网络
     * @param flowable
     * @param <T>
     * @return
     */
    public <T> BaseHttpSubscriber<T> request(Flowable<BaseBean<T>> flowable){
        BaseHttpSubscriber<T> baseHttpSubscriber = new BaseHttpSubscriber<>(); //RxJava Subscriber回调
        flowable.subscribeOn(Schedulers.io()) //解决背压
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(baseHttpSubscriber);
        return baseHttpSubscriber;
    }
}
