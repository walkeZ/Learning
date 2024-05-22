package com.walker.mvvmlearn.net.retrofit2.callback;

import com.walker.mvvmlearn.net.model.bean.BaseBean;
import com.walker.mvvmlearn.net.retrofit2.IView;
import com.walker.mvvmlearn.net.retrofit2.exception.ExceptionHandle;
import com.walker.mvvmlearn.net.retrofit2.exception.OkHttpException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author walker
 * @date 2024/5/21
 * @description 标准回调
 */
public class CallBackObserver<T> implements Observer<T> {
    private IResponseCallBack<T> mListener;
    private IView iView;

    public CallBackObserver(IView iView, IResponseCallBack<T> listener) {
        this.mListener = listener;
        this.iView = iView;
    }

    @Override
    public void onSubscribe(Disposable d) {
        /**
         * 这里可以 显示加载圈等
         */
    }

    /**
     * 成功
     *
     * @param data
     */
    @Override
    public void onNext(T data) {
        if (mListener != null && !iView.isFinishing()) {
            BaseBean baseBean = (BaseBean) data;
            /**
             * 是否成功
             */
            if (baseBean.isSuccess()) {
                mListener.onSuccess(data);
            } else {
                mListener.onFail(new OkHttpException(baseBean.getErrorCode(), baseBean.getErrorMsg()));
            }
        }

    }

    /**
     * 失败
     *
     * @param e
     */
    @Override
    public void onError(Throwable e) {
        onComplete();
        if (mListener != null && !iView.isFinishing()) {
            /**
             * 处理失败原因
             */
            OkHttpException okHttpException = ExceptionHandle.handleException(e, iView);
            if (okHttpException != null) {
                mListener.onFail(okHttpException);
            }
        }

    }

    @Override
    public void onComplete() {
        /**
         * 这里可以 关闭加载圈等
         */
    }
}
