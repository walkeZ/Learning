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
        // https://www.jianshu.com/p/b364646f809d
        /**
         * subscribeOn() 指定的就是发射事件的线程
         * observerOn 指定的就是订阅者接收事件的线程。
         *
         * Retrofit 设计采用观察者模式：
         *  Observable ( 被观察者 ) / Observer ( 观察者 )
         *  Flowable （被观察者）/ Subscriber （观察者）--支持被压
         *  -- RxJva详解：被压 、Observable、Flowable等 ；https://www.jianshu.com/p/0cd258eecf60
         *  --
         */
        BaseHttpSubscriber<T> baseHttpSubscriber = new BaseHttpSubscriber<>(); //RxJava Subscriber回调
        flowable.subscribeOn(Schedulers.io()) //解决背压。背压是指在异步场景中，被观察者发送事件速度远快于观察者的处理速度的情况下，一种告诉上游的被观察者降低发送速度的策略。
        .observeOn(AndroidSchedulers.mainThread()) //指定的就
        .subscribe(baseHttpSubscriber);
        return baseHttpSubscriber;
    }
}
