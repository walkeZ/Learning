package com.walker.mvvmlearn.net.retrofit2.base;

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
 */
public class BaseHttpSubscriber<T> implements Subscriber<BaseBean<T>> {


    private OkHttpException ex;

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
        if (t.getErrorCode() == NetConfig.SUCCESS_CODE) {
            onFinish(t);
        } else {
            ex = ExceptionHandle.handleException(new OkHttpException(t.getErrorCode(), t.getErrorMsg()));
            getErrorDto(ex);
        }
    }

    @Override
    public void onError(Throwable t) {
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
