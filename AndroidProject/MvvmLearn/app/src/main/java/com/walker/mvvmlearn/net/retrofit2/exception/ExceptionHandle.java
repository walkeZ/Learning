package com.walker.mvvmlearn.net.retrofit2.exception;

import android.net.ParseException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.walker.mvvmlearn.net.model.bean.BaseBean;
import com.walker.mvvmlearn.net.retrofit2.IView;

import org.json.JSONException;

import java.io.IOException;
import java.net.ConnectException;

import okhttp3.ResponseBody;
import retrofit2.HttpException;

/**
 * @author walker
 * @date 2024/5/20
 * @description 自定义错误解析类 处理类，一些默认处理
 */
public class ExceptionHandle {
    private static final int TIMEOUT_ERROR = -1;
    private static final int JSON_ERROR = -2;
    private static final int NETWORK_ERROR = -3;
    private static final int OTHER_ERROR = -4;

    private static final String TIMEOUTMSG = "请求超时";
    private static final String JSONMSG = "解析错误";
    private static final String NETWORKMSG = "连接失败";
    private static final String OTHERMSG = "未知错误";

    /**
     * 根据接口定义 错误码等于999 为token失效 需要重新登录获取新的token
     */
    private static final int TOKENLOGIN = 999;

    public static OkHttpException handleException(Throwable e, IView iView) {
        OkHttpException ex = null;
        if (e instanceof HttpException) {
            ResponseBody body = ((HttpException) e).response().errorBody();
            try {
                Gson gson = new GsonBuilder().serializeNulls().create();
                String jsonStr = body.string();
                BaseBean baseBean = gson.fromJson(jsonStr, BaseBean.class);

                /**
                 * token失效 重新登录
                 */
                if (baseBean.getErrorCode() == TOKENLOGIN) {
                    iView.toLogin();
                } else {
                    ex = new OkHttpException(baseBean.getErrorCode(), baseBean.getErrorMsg());
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return ex;
        } else if (e instanceof java.net.SocketTimeoutException) {
            ex = new OkHttpException(TIMEOUT_ERROR, TIMEOUTMSG);
            return ex;
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {
            ex = new OkHttpException(JSON_ERROR, JSONMSG);
            return ex;
        } else if (e instanceof ConnectException) {
            ex = new OkHttpException(NETWORK_ERROR, NETWORKMSG);
            return ex;
        } else {
            ex = new OkHttpException(OTHER_ERROR, OTHERMSG);
            return ex;
        }
    }
}
