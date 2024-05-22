package com.walker.mvvmlearn.net.retrofit2.base;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.walker.mvvmlearn.net.retrofit2.base.vo.VoBase;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

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
public class BaseHttpObserver<D extends VoBase> implements Observer<D> {
    private static final String TAG = "BaseHttpObserver";

    public BaseHttpObserver() {
        this.d = new MutableLiveData();
    }

    MutableLiveData<D> d;

    public MutableLiveData<D> getData() {
        return d;
    }

    public void setData(D d) {
        this.d.setValue(d);
    }

    /**
     * 当订阅时会被调用
     * Disposable：单次的、一次的。 可以主动取消(Rx事件的，不一定是网络)连接。可按业务自定义拓展取消，此处不拓展来说
     *
     * @param disposable the Disposable instance whose {@link Disposable#dispose()} can
     *                   be called anytime to cancel the connection
     */
    @Override
    public void onSubscribe(Disposable disposable) {
        // 某角度来说也代表着开始回调。可以开启进度条等常规异步加载UI
    }

    /**
     * 普通的事件。将要处理的事件添加到事件队列中
     * 成功
     *
     * @param d the item emitted by the Observable
     */
    @Override
    public void onNext(D d) {
        Log.i(TAG, "onNext: " + d.errorCode);
        setData(d);
    }

    /**
     * 事件队列异常。在事件处理过程中出现异常时，onError方法会触发，同时队列会自动终止，不允许再有事件发出
     *
     * @param e the exception encountered by the Observable
     */
    @Override
    public void onError(Throwable e) {
        Log.i(TAG, "onError: " + e.getMessage());
        VoBase voBase = new VoBase();
        voBase.errorCode = -1;
        setData((D) voBase);
        // TODO: 2024/5/22 自定义拓展的失败处理
    }

    /**
     * 事件队列完结，RxJava不仅把每个事件单独处理，而且还会把它们看作一个队列。当不会有新的onNext发出时，需要触发onComplete方法作为完成标志。
     */
    @Override
    public void onComplete() {
        // 关闭开启进度条等
    }
}
