//package com.walker.mvvmlearn.net.retrofit2.callback;
//
//import android.text.TextUtils;
//import android.util.Log;
//
//import com.google.gson.JsonParseException;
//import com.walker.mvvmlearn.net.retrofit2.IModel;
//import com.walker.mvvmlearn.net.retrofit2.IView;
//import com.walker.mvvmlearn.net.retrofit2.NetConfig;
//import com.walker.mvvmlearn.net.retrofit2.exception.OkHttpException;
//
//import org.json.JSONException;
//
//import java.net.SocketTimeoutException;
//import java.net.UnknownHostException;
//
//import io.reactivex.Observable;
//import io.reactivex.Observer;
//import io.reactivex.subscribers.ResourceSubscriber;
//
///**
// * Tyj项目的但是ApiServer的接收不同，弃用
// */
//public abstract class ApiSubscriber<T extends IModel> implements Observer<T> {
//
//    private static final String TAG = "请求结果";
//    private boolean isShowErrorMsg;
//    private boolean isShowLoading  = true;
//    private IView mIView;
//
//    public abstract void onSuccess(T t);
//
//    public void onFail(OkHttpException exception) {
//
//    }
//
//    @Override
//    public void onComplete() {
//        if (mIView != null)
//            mIView.hideLoadDialog();
//    }
//
//    /**
//     * @param IView          view引用
//     * @param isShowErrorMsg 是否toast错误信息  默认toast
//     * @param isShowLoading  显示进度条
//     */
//    protected ApiSubscriber(IView IView, boolean isShowErrorMsg, boolean isShowLoading) {
//        this.isShowErrorMsg = isShowErrorMsg;
//        this.isShowLoading = isShowLoading;
//        mIView = IView;
//        if (isShowLoading) {
//            mIView.showLoadDialog();
//        }
//    }
//
//    /**
//     * @param IView          view引用
//     * @param isShowErrorMsg 是否toast错误信息  默认toast
//     */
//    protected ApiSubscriber(IView IView, boolean isShowErrorMsg) {
//        this.isShowErrorMsg = isShowErrorMsg;
//        mIView = IView;
//        if (isShowLoading) {
//            mIView.showLoadDialog();
//        }
//    }
//
//    /**
//     * @param IView view引用
//     */
//    protected ApiSubscriber(IView IView) {
//        this.isShowErrorMsg = true;
//        mIView = IView;
//    }
//
//    @Override
//    public void onNext(T t) {
//        if (mIView.isFinishing()) {
//            Log.e(TAG, "onNext: isFinishing");
//            return;
//        }
//        if (mIView.isOnPause()) {
//            Log.e(TAG, "onNext: isOnPause");
//            return;
//        }
//        try {
//            if (t.getErrorCode() == NetConfig.SUCCESS_CODE) {
//                onSuccess(t);
//            } else {
//                onError(new OkHttpException(t.getErrorCode(), t.getErrorMsg()));
//            }
//            if (mIView != null && !TextUtils.isEmpty(t.getErrorMsg())) {
//                Log.i("walke: ", " ApiSubscriber:  onNext:-------> " + t.getErrorMsg());
//                mIView.toast(t.getErrorMsg());
//            }
//        } catch (Exception e) {
//            onError(e);
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void onError(Throwable t) {
//        if (mIView != null) {
//            mIView.hideLoadDialog();
//        }
//        if (t != null) {
//            t.printStackTrace();
//            OkHttpException error = null;
//            if (t instanceof UnknownHostException || t instanceof SocketTimeoutException) {
//                Log.i("walke", "ApiSubscriber onError(): ");
//                error = new OkHttpException(100, "网络不给力");
//            } else if (t instanceof JSONException || t instanceof JsonParseException) {
//                Log.e(TAG, "onError: " + t.getMessage());
//                error = new OkHttpException(100,"网络不给力");
//            }
//
//            onFail(error);
//        } else {
//            onFail(new OkHttpException( 100,"网络不给力"));
//        }
//    }
//
//}
