package com.walker.mvvmlearn.net.retrofit2.base;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.walker.mvvmlearn.net.model.bean.BaseBean;
import com.walker.mvvmlearn.net.retrofit2.NetConfig;
import com.walker.mvvmlearn.net.retrofit2.exception.ExceptionHandle;
import com.walker.mvvmlearn.net.retrofit2.exception.OkHttpException;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/**
 * 自定义请服务器被观察者
 *
 * @author twilight
 * @Time 2019-07-21
 * <p>
 * 对观察者进行自定义封装，定义一个数据属性(LiveData)，使得该属性是(网络)异步返回的结果。
 * 对外提供一个获取属性LiveData的接口————get()  然后可以与ViewModel关联上。
 * 网络请求时使用该观察者观察网络请求。如 xxx.BannerRepository#getBanner()的：
 * > BaseHttpSubscriber<List<BannerBean>> httpSubscriber = new BaseHttpSubscriber<>();
 * > NetRequest.createApi().getBannerData().subscribe(httpSubscriber);
 * > return httpSubscriber.get();
 */
public class BaseHttpSubscriber<T> implements Subscriber<BaseBean<T>> {


    private OkHttpException ex;
    private static final String TAG = "BaseHttpSubscriber";

    public BaseHttpSubscriber() {
        data = new MutableLiveData();
    }

    private MutableLiveData<BaseBean<T>> data;

    public MutableLiveData<BaseBean<T>> get() {
        return data;
    }

    public void set(BaseBean<T> t) {
        this.data.setValue(t);
    }

    public void onFinish(BaseBean<T> t) {
        set(t);
    }

    @Override
    public void onSubscribe(Subscription s) {
        // 观察者接收事件 = 1个
        s.request(1);
    }

    @Override
    public void onNext(BaseBean<T> t) {
        Log.i(TAG, "onNext: " + t.getErrorCode());
        if (t.getErrorCode() == NetConfig.SUCCESS_CODE) {
            onFinish(t);
        } else {
            ex = ExceptionHandle.handleException(new OkHttpException(t.getErrorCode(), t.getErrorMsg()));
            getErrorDto(ex);
        }
    }

    @Override
    public void onError(Throwable t) {
        Log.i(TAG, "onError: " + t.getMessage());
        ex = ExceptionHandle.handleException(t);
        getErrorDto(ex);
    }

    /**
     * 初始化错误的dto
     *
     * @param ex
     */
    private void getErrorDto(OkHttpException ex) {
        BaseBean dto = new BaseBean();
        dto.setErrorCode(ex.getEcode());
        dto.setErrorMsg(ex.getEmsg());
        onFinish((BaseBean<T>) dto);
    }

    @Override
    public void onComplete() {
    }

}
