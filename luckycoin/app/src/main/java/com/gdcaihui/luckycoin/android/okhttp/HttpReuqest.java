package com.gdcaihui.luckycoin.android.okhttp;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.WindowManager;
import android.webkit.WebView;

import com.gdcaihui.luckycoin.android.test.personal.LoginActivity;
import com.gdcaihui.luckycoin.android.base.BaseActivity;
import com.gdcaihui.luckycoin.android.config.Api;
import com.gdcaihui.luckycoin.android.config.Contants;
import com.gdcaihui.luckycoin.android.config.Request;
import com.gdcaihui.luckycoin.android.control.LoadDialog;
import com.gdcaihui.luckycoin.android.entity.vo.Message;
import com.gdcaihui.luckycoin.android.entity.vo.VersionInfo;
import com.gdcaihui.luckycoin.android.entity.vo.VoBase;
import com.gdcaihui.luckycoin.android.okhttp.callback.Callback;
import com.gdcaihui.luckycoin.android.utils.AESUtil;
import com.gdcaihui.luckycoin.android.utils.AppUtil;
import com.gdcaihui.luckycoin.android.utils.CompressUtil;
import com.gdcaihui.luckycoin.android.utils.MD5Util;
import com.gdcaihui.luckycoin.android.utils.NetWorkUtil;
import com.gdcaihui.luckycoin.android.utils.PermissionUtils;
import com.gdcaihui.luckycoin.android.utils.SharepreUtil;
import com.gdcaihui.luckycoin.android.utils.ToastUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.internal.LinkedTreeMap;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Cookie;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Response;

/**
 * @author View
 * @date 2016/11/27 15:41
 */
public class HttpReuqest {

    public interface CallBack<T> {

        void onSuccess(Message message, T result);

        void onError(Exception e);

        void onFinish();

    }

    private Context context;

    public HttpReuqest(Context context) {
        this.context = context;
    }

    @NonNull
    private String getPostHeader() {
        String s = getPhoneSystem() + " " + getPacakename() + "/" + AppUtil.getVersionName(context);
        return s;
    }

    public String getPacakename() {
        String pacakeName = context.getPackageName().replaceAll("\\.", "_");
        return pacakeName;
    }

    public String getPhoneSystem() {
        String userAgentString = new WebView(context).getSettings().getUserAgentString();
        return userAgentString;
        /*String release = Build.VERSION.RELEASE;
        icon_return "Android/"+ release;*/
    }

    public synchronized void  sendMessage(final String type, Object param, boolean showDialog, final CallBack myCallBack) {
        final Dialog loginLoadDialog = LoadDialog.createDialog(context, "请稍等");
        if (showDialog) {
            loginLoadDialog.show();
        } else {
            loginLoadDialog.dismiss();
        }

        String key = MD5Util.MD5Encode(Api.KEY);
        final String secretKey = Api.SECRET_KEY;

        Message message = new Message();
        message.setType(type);
        message.setVersion(Api.VERSION);

        if (param != null) {
            if (param instanceof String)
                message.setMessage(param.toString());
            else {
                String voMessage = new GsonBuilder().create().toJson(param);
                message.setMessage(voMessage);
            }
        }

        String sign = MD5Util.MD5Encode(message.getId() + message.getVersion() + message.getType() + message.getMessage() + Api.CLIENT_SALT);
        message.setSign(sign);

        String messageJson = new GsonBuilder().create().toJson(message);
        try {
            byte[] data = CompressUtil.compress(messageJson);
            data = AESUtil.encrypt(data, secretKey);
            OkHttpUtils
                    .post()
                    .url(Request.API_URL)
                    .addHeader("User-Agent", getPostHeader())
                    .addParams(Api.PARAMS_KEY, key)
                    .addParams(Api.PARAMS_MESSAGE, Base64.encodeToString(data, Base64.DEFAULT))
                    .build()
                    .execute(new Callback<String>() {
                        @Override
                        public String parseNetworkResponse(Response response, int id) throws Exception {
                            return response.body().string();
                        }

                        @Override
                        public void onError(Call call, Exception e, int id) {
                            if (loginLoadDialog != null && loginLoadDialog.isShowing())
                                loginLoadDialog.dismiss();

                            if (myCallBack != null) {
                                myCallBack.onError(e);
                                if (!NetWorkUtil.isNetworkConnected(context)) {
                                    ToastUtil.showToast(context,Contants.ERROR_NO_NET);
                                } else {
                                    ToastUtil.showToast(context, Contants.ERROR_SERVICE_BACK);//e.getMessage() + ""
                                }
                                myCallBack.onFinish();

                            }
                            return;
                        }

                        @Override
                        public void onResponse(String result, int id) {
                            try {
                                if (loginLoadDialog != null && loginLoadDialog.isShowing())
                                    loginLoadDialog.dismiss();

                                if (result == null || TextUtils.isEmpty(result)) {
                                    if (myCallBack != null) {
                                        myCallBack.onError(new Exception(Contants.ERROR_SERVICE));
                                        ToastUtil.showToast(context,Contants.ERROR_SERVICE);
                                        myCallBack.onFinish();
                                    }
                                    return;
                                }
                                byte[] data = Base64.decode(result, Base64.DEFAULT);
                                data = AESUtil.decrypt(data, secretKey);
                                String messageJson = CompressUtil.decompress(data);
                                Message message = new Gson().fromJson(messageJson, Message.class);

                                if (myCallBack != null) {
                                    if (message == null) {
                                        throw new Exception(Contants.ERROR_SERVICE_BACK_DATA);
                                    } else {
                                        String sign = MD5Util.MD5Encode(message.getId() + message.getVersion() + message.getType() + message.getMessage() + Api.CLIENT_SALT);
                                        if (sign == null || message.getSign() == null || !sign.equalsIgnoreCase(message.getSign()))
                                            throw new Exception("数据验签失败");

                                        Object paramBack = null;
                                        if (message.getMessage() != null) {
                                            Type[] types = myCallBack.getClass().getGenericInterfaces();
                                            if (types.length > 0) {
                                                Type type = ((ParameterizedType) types[0]).getActualTypeArguments()[0];
                                                try {
                                                    paramBack = new Gson().fromJson(message.getMessage(), type);
                                                } catch (JsonSyntaxException ex) {
                                                    throw new Exception(message.getMessage());
                                                }
                                                if (paramBack != null && (paramBack instanceof LinkedTreeMap || (paramBack instanceof ArrayList && ((ArrayList) paramBack).size() > 0) && ((ArrayList) paramBack).get(0) instanceof LinkedTreeMap))
                                                    throw new Exception(Contants.ERROR_SERVICE_BACK_DATA_CAST);
                                            }
                                        }

                                        if (paramBack instanceof VoBase) {
                                            VoBase resultVoBase = (VoBase) paramBack;
                                            if (Api.VERSION_ERROR == resultVoBase.getCode()) {
                                                // TODO: 2017/3/1 //版本异常
                                                try {
                                                    final VersionInfo versionInfo = new Gson().fromJson(message.getMessage(), VersionInfo.class);

                                                    if (!TextUtils.isEmpty(versionInfo.getUpdateDownloadUrl())) {//可更新
                                                        boolean isFirstError = SharepreUtil.getBoolean(context, Contants.IS_FIRST_VERSION_ERROR, true);
                                                        if (isFirstError) {
                                                            SharepreUtil.putBoolean(context, Contants.IS_FIRST_VERSION_ERROR, false);
                                                            LoadDialog.versionUpdateDialogTwoButton(context, versionInfo.getText() + "", "退出应用", "下载更新", versionInfo.getUpdateVersionRemark(), new LoadDialog.DialogTwoButtonClickListener() {
                                                                @Override
                                                                public void leftOnClick(WindowManager.LayoutParams lp, Dialog dialog) {
                                                                    ((BaseActivity) context).getLucyCoinApplication().exit();
                                                                }

                                                                @Override
                                                                public void rightOnClick(WindowManager.LayoutParams lp, Dialog dialog) {
                                                                    /*if (dialog!=null&&dialog.isShowing())
                                                                        dialog.dismiss();
                                                                    AppUtil.loadApk(context, versionInfo);*/
                                                                    if (Build.VERSION.SDK_INT < 23) {
                                                                        if (dialog != null && dialog.isShowing())
                                                                            dialog.dismiss();
                                                                        AppUtil.loadApk(context, versionInfo);
                                                                    }else {
                                                                        //if (isAutoRequestCheck) {
                                                                        if (PermissionUtils.checkPermissionSetLack(context, Contants.PERMISSION_SDCARD)) {
                                                                            ((BaseActivity)context).getLucyCoinApplication().setVersionInfo(versionInfo);
                                                                            ((BaseActivity)context).getLucyCoinApplication().setDialog(dialog);
                                                                            //申请权限(系统方法)  用户选择允许或拒绝后会回调onRequestPermissionsResult
                                                                            ((BaseActivity)context).requestPermissions(Contants.PERMISSION_SDCARD, Contants.PERMISSION_SDCARD_REQUEST_CODE); //去请求权限
                                                                        } else {
                                                                            if (dialog != null && dialog.isShowing())
                                                                                dialog.dismiss();
                                                                            AppUtil.loadApk(context, versionInfo);
                                                                        }
                                                                        //}
                                                                    }
                                                                }
                                                            });

                                                        }

                                                    } else {//仅能退出
                                                        LoadDialog.versionUpdateDialogOneButton(context, versionInfo.getText() + "", "退出应用", new LoadDialog.OneButtonClickListener() {
                                                            @Override
                                                            public void onClicked() {
                                                                ((BaseActivity) context).getLucyCoinApplication().exit();
                                                            }
                                                        });
                                                    }
                                                    if (myCallBack != null) {
                                                        myCallBack.onFinish();
                                                    }
                                                    return;
                                                } catch (JsonSyntaxException ex) {
                                                    throw new Exception(message.getMessage());
                                                }

                                            } else {
                                                if (Api.NO_LOGIN == resultVoBase.getCode()) {
                                                    ((BaseActivity) context).getLucyCoinApplication().setMemberInfo(null);//只要返回是Api.NO_LOGIN，就清一遍账户缓存，成功后再设置
                                                    // 去登陆
                                                    Intent intent = new Intent(context, LoginActivity.class);
                                                    Bundle extras = new Bundle();
                                                    intent.putExtras(extras);
                                                    ((BaseActivity) context).startActivityForResult(intent, Contants.LOGIN_SUCCESS_CODE);
                                                    if (myCallBack != null) {
                                                        myCallBack.onFinish();
                                                    }
                                                    return;
                                                } else  {

                                                    saveCookie(OkHttpUtils.getInstance().getOkHttpClient(), HttpUrl.parse(Request.API_URL));
                                                }
                                            }
                                        }
                                        myCallBack.onSuccess(message, paramBack);
                                    }
                                }
                            } catch (Exception e) {
                                Log.e("请求服务器回调处理异常", ""+e);
                                if (myCallBack != null) {
                                    myCallBack.onError(e);
                                    myCallBack.onFinish();
                                }
                            }
                            if (myCallBack != null) {
                                myCallBack.onFinish();
                            }
                            return;
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return;
    }


    public void sendMessage(final String type, Object param, boolean showDialog, final Dialog loadDialog, final CallBack callBack1) {
        final Dialog loginLoadDialog = LoadDialog.createDialog(context, "请稍等");
        if (showDialog) {
            loginLoadDialog.show();
        } else {
            loginLoadDialog.dismiss();
        }

        String key = MD5Util.MD5Encode(Api.KEY);
        final String secretKey = Api.SECRET_KEY;

        Message message = new Message();
        message.setType(type);
        message.setVersion(Api.VERSION);

        if (param != null) {
            if (param instanceof String)
                message.setMessage(param.toString());
            else {
                String voMessage = new GsonBuilder().create().toJson(param);
                message.setMessage(voMessage);
            }
        }

        String sign = MD5Util.MD5Encode(message.getId() + message.getVersion() + message.getType() + message.getMessage() + Api.CLIENT_SALT);
        message.setSign(sign);

        String messageJson = new GsonBuilder().create().toJson(message);
        try {
            byte[] data = CompressUtil.compress(messageJson);
            data = AESUtil.encrypt(data, secretKey);
            OkHttpUtils
                    .post()
                    .url(Request.API_URL)
                    .addHeader("User-Agent", getPostHeader())
                    .addParams(Api.PARAMS_KEY, key)
                    .addParams(Api.PARAMS_MESSAGE, Base64.encodeToString(data, Base64.DEFAULT))
                    .build()
                    .execute(new Callback<String>() {
                        @Override
                        public String parseNetworkResponse(Response response, int id) throws Exception {
                            return response.body().string();
                        }

                        @Override
                        public void onError(Call call, Exception e, int id) {
                            if (loginLoadDialog != null && loginLoadDialog.isShowing())
                                loginLoadDialog.dismiss();

                            if (callBack1 != null) {
                                callBack1.onError(e);
                                if (!NetWorkUtil.isNetworkConnected(context)) {
                                    ToastUtil.showToast(context,Contants.ERROR_NO_NET);
                                } else {
                                    ToastUtil.showToast(context, Contants.ERROR_SERVICE_BACK);//e.getMessage() + ""
                                }
                                callBack1.onFinish();

                            }
                            return;
                        }

                        @Override
                        public void onResponse(String result, int id) {
                            try {
                                if (loginLoadDialog != null && loginLoadDialog.isShowing())
                                    loginLoadDialog.dismiss();

                                if (result == null || TextUtils.isEmpty(result)) {
                                    if (callBack1 != null) {
                                        callBack1.onError(new Exception(Contants.ERROR_SERVICE));
                                        ToastUtil.showToast(context,Contants.ERROR_SERVICE);
                                        callBack1.onFinish();
                                    }
                                    return;
                                }
                                byte[] data = Base64.decode(result, Base64.DEFAULT);
                                data = AESUtil.decrypt(data, secretKey);
                                String messageJson = CompressUtil.decompress(data);
                                Message message = new Gson().fromJson(messageJson, Message.class);

                                if (callBack1 != null) {
                                    if (message == null) {
                                        throw new Exception(Contants.ERROR_SERVICE_BACK_DATA);
                                    } else {
                                        String sign = MD5Util.MD5Encode(message.getId() + message.getVersion() + message.getType() + message.getMessage() + Api.CLIENT_SALT);
                                        if (sign == null || message.getSign() == null || !sign.equalsIgnoreCase(message.getSign()))
                                            throw new Exception("数据验签失败");

                                        Object param = null;
                                        if (message.getMessage() != null) {
                                            Type[] types = callBack1.getClass().getGenericInterfaces();
                                            if (types.length > 0) {
                                                Type type = ((ParameterizedType) types[0]).getActualTypeArguments()[0];
                                                try {
                                                    param = new Gson().fromJson(message.getMessage(), type);
                                                } catch (JsonSyntaxException ex) {
                                                    throw new Exception(message.getMessage());
                                                }
                                                if (param != null && (param instanceof LinkedTreeMap || (param instanceof ArrayList && ((ArrayList) param).size() > 0) && ((ArrayList) param).get(0) instanceof LinkedTreeMap))
                                                    throw new Exception(Contants.ERROR_SERVICE_BACK_DATA_CAST);
                                            }
                                        }

                                        if (param instanceof VoBase) {
                                            VoBase resultVoBase = (VoBase) param;
                                            if (Api.VERSION_ERROR == resultVoBase.getCode()) {
                                                // TODO: 2017/3/1 //版本异常
                                                if (loadDialog!=null&&loadDialog.isShowing()){
                                                    loadDialog.dismiss();
                                                }
                                                try {
                                                    final VersionInfo versionInfo = new Gson().fromJson(message.getMessage(), VersionInfo.class);

                                                    if (!TextUtils.isEmpty(versionInfo.getUpdateDownloadUrl())) {//可更新

                                                        boolean isFirstError = SharepreUtil.getBoolean(context, Contants.IS_FIRST_VERSION_ERROR, true);//避免两次或多次创建dialog

                                                        if (isFirstError) {

                                                            SharepreUtil.putBoolean(context, Contants.IS_FIRST_VERSION_ERROR, false);
                                                            LoadDialog.versionUpdateDialogTwoButton(context, versionInfo.getText() + "", "退出应用", "下载更新", versionInfo.getUpdateVersionRemark(), new LoadDialog.DialogTwoButtonClickListener() {
                                                                @Override
                                                                public void leftOnClick(WindowManager.LayoutParams lp, Dialog dialog) {
                                                                    ((BaseActivity) context).getLucyCoinApplication().exit();
                                                                }

                                                                @Override
                                                                public void rightOnClick(WindowManager.LayoutParams lp, Dialog dialog) {
                                                                    /*if (dialog!=null&&dialog.isShowing())
                                                                        dialog.dismiss();
                                                                    AppUtil.loadApk(context, versionInfo);*/
                                                                    if (Build.VERSION.SDK_INT < 23) {
                                                                        if (dialog != null && dialog.isShowing())
                                                                            dialog.dismiss();
                                                                        AppUtil.loadApk(context, versionInfo);
                                                                    }else {
                                                                        //if (isAutoRequestCheck) {
                                                                        if (PermissionUtils.checkPermissionSetLack(context, Contants.PERMISSION_SDCARD)) {
                                                                            ((BaseActivity)context).getLucyCoinApplication().setVersionInfo(versionInfo);
                                                                            ((BaseActivity)context).getLucyCoinApplication().setDialog(dialog);
                                                                            //申请权限(系统方法)  用户选择允许或拒绝后会回调onRequestPermissionsResult
                                                                            ((BaseActivity)context).requestPermissions(Contants.PERMISSION_SDCARD, Contants.PERMISSION_SDCARD_REQUEST_CODE); //去请求权限
                                                                        } else {
                                                                            if (dialog != null && dialog.isShowing())
                                                                                dialog.dismiss();
                                                                            AppUtil.loadApk(context, versionInfo);
                                                                        }
                                                                        //}
                                                                    }
                                                                }
                                                            });

                                                        }

                                                    } else {//仅能退出

                                                        LoadDialog.versionUpdateDialogOneButton(context, versionInfo.getText() + "", "退出应用", new LoadDialog.OneButtonClickListener() {
                                                            @Override
                                                            public void onClicked() {
                                                                ((BaseActivity) context).getLucyCoinApplication().exit();
                                                            }
                                                        });
                                                    }
                                                    if (callBack1 != null) {
                                                        callBack1.onFinish();
                                                    }
                                                    return;
                                                } catch (JsonSyntaxException ex) {
                                                    throw new Exception(message.getMessage());
                                                }

                                            } else {
                                                if (Api.NO_LOGIN == resultVoBase.getCode()) {
                                                    // ((BaseActivity) context).getLucyCoinApplication().setMemberInfo(null);//只要返回是Api.NO_LOGIN，就清一遍账户缓存，成功后再设置
                                                    // 去登陆
                                                    Intent intent = new Intent(context, LoginActivity.class);
                                                    Bundle extras = new Bundle();
                                                    intent.putExtras(extras);
                                                    ((BaseActivity) context).startActivityForResult(intent, Contants.LOGIN_SUCCESS_CODE);
                                                    if (callBack1 != null) {
                                                        callBack1.onFinish();
                                                    }
                                                    return;
                                                } else  {

                                                    saveCookie(OkHttpUtils.getInstance().getOkHttpClient(), HttpUrl.parse(Request.API_URL));
                                                }
                                            }
                                        }
                                        callBack1.onSuccess(message, param);
                                    }
                                }
                            } catch (Exception e) {
                                Log.e("请求服务器回调处理异常", ""+e);
                                if (callBack1 != null) {
                                    callBack1.onError(e);
                                    ToastUtil.showToast(context,e.getMessage() + "");
                                    callBack1.onFinish();
                                }
                            }
                            if (callBack1 != null) {
                                callBack1.onFinish();
                            }
                            return;
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return;
    }

    /**
     * 获取标准 Cookie ，并存储
     * 用：saveCookie(OkHttpUtils.getInstance().getOkHttpClient(), HttpUrl.parse(mUrl));
     * @param httpClient
     * @param httpUrl
     */
    public void saveCookie(OkHttpClient httpClient, HttpUrl httpUrl) {
        List<Cookie> cookies = httpClient.cookieJar().loadForRequest(httpUrl);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < cookies.size(); i++) {
            Cookie cookie = cookies.get(i);
            String cookieStr = cookie.toString();
            if (!TextUtils.isEmpty(cookieStr)) {
                sb.append(cookieStr + "=");//最后的“=”不能少
            }
        }
        SharepreUtil.putString(context, Contants.WEBVIEW_COOKIE, sb.toString());
    }

    public void sendMessagePersonalFragmentOrExit( boolean showDialog, final String type, Object param, final CallBack callBack) {

        String url;
        final Dialog requestDialog = LoadDialog.createDialog(context, "请稍等");
        if (showDialog){
            requestDialog.show();
        }else {
            requestDialog.dismiss();
        }

        String key = MD5Util.MD5Encode(Api.KEY);
        final String secretKey = Api.SECRET_KEY;

        Message message = new Message();
        message.setType(type);
        message.setVersion(Api.VERSION);

        if (param != null) {
            if (param instanceof String)
                message.setMessage(param.toString());
            else {
                String voMessage = new GsonBuilder().create().toJson(param);
                message.setMessage(voMessage);
            }
        }

        String sign = MD5Util.MD5Encode(message.getId() + message.getVersion() + message.getType() + message.getMessage() + Api.CLIENT_SALT);
        message.setSign(sign);

        String messageJson = new GsonBuilder().create().toJson(message);
        try {
            byte[] data = CompressUtil.compress(messageJson);
            data = AESUtil.encrypt(data, secretKey);
            OkHttpUtils
                    .post()
                    .url(Request.API_URL)
                    .addHeader("User-Agent", getPostHeader())
                    .addParams(Api.PARAMS_KEY, key)
                    .addParams(Api.PARAMS_MESSAGE, Base64.encodeToString(data, Base64.DEFAULT))
                    .build()
                    .execute(new Callback<String>() {
                        @Override
                        public String parseNetworkResponse(Response response, int id) throws Exception {
                            return response.body().string();
                        }

                        @Override
                        public void onError(Call call, Exception e, int id) {

                            if (requestDialog != null && requestDialog.isShowing())
                                requestDialog.dismiss();

                            if (callBack != null) {
                                callBack.onError(e);
                                if (!NetWorkUtil.isNetworkConnected(context)) {
                                    ToastUtil.showToast(context,Contants.ERROR_NO_NET);
                                } else {
                                    ToastUtil.showToast(context,Contants.ERROR_SERVICE_BACK);//e.getMessage() + ""
                                }
                                callBack.onFinish();
                            }
                            return;
                        }

                        @Override
                        public void onResponse(String result, int id) {
                            try {
                                if (requestDialog != null && requestDialog.isShowing())
                                    requestDialog.dismiss();
                                if (result == null || TextUtils.isEmpty(result)) {
                                    if (callBack != null) {
                                        callBack.onError(new Exception(Contants.ERROR_SERVICE));
                                        ToastUtil.showToast(context,Contants.ERROR_SERVICE);
                                        callBack.onFinish();
                                    }
                                    return;
                                }
                                byte[] data = Base64.decode(result, Base64.DEFAULT);
                                data = AESUtil.decrypt(data, secretKey);
                                String messageJson = CompressUtil.decompress(data);
                                Message message = new Gson().fromJson(messageJson, Message.class);

                                if (callBack != null) {
                                    if (message == null) {
                                        throw new Exception(Contants.ERROR_SERVICE_BACK_DATA);
                                    } else {
                                        String sign = MD5Util.MD5Encode(message.getId() + message.getVersion() + message.getType() + message.getMessage() + Api.CLIENT_SALT);
                                        if (sign == null || message.getSign() == null || !sign.equalsIgnoreCase(message.getSign()))
                                            throw new Exception("数据验签失败");

                                        Object param = null;
                                        if (message.getMessage() != null) {
                                            Type[] types = callBack.getClass().getGenericInterfaces();
                                            if (types.length > 0) {
                                                Type type = ((ParameterizedType) types[0]).getActualTypeArguments()[0];
                                                try {
                                                    param = new Gson().fromJson(message.getMessage(), type);
                                                } catch (JsonSyntaxException ex) {
                                                    throw new Exception(message.getMessage());
                                                }
                                                if (param != null && (param instanceof LinkedTreeMap || (param instanceof ArrayList && ((ArrayList) param).size() > 0) && ((ArrayList) param).get(0) instanceof LinkedTreeMap))
                                                    throw new Exception(Contants.ERROR_SERVICE_BACK_DATA_CAST);
                                            }
                                        }

                                        if (param instanceof VoBase) {
                                            VoBase resultVoBase = (VoBase) param;
                                            if (Api.VERSION_ERROR == resultVoBase.getCode()) {
                                                // TODO: 2017/3/1 //版本异常
                                                try {
                                                    final VersionInfo versionInfo = new Gson().fromJson(message.getMessage(), VersionInfo.class);
                                                    String updateDownloadUrl = versionInfo.getUpdateDownloadUrl();
                                                    if (updateDownloadUrl != null && updateDownloadUrl.length() > 0) {
                                                        //可更新

                                                    } else {
                                                        //仅能退出

                                                    }
                                                    return;
                                                } catch (JsonSyntaxException ex) {
                                                    throw new Exception(message.getMessage());
                                                }
                                            } else {

                                            }
                                        }

                                        callBack.onSuccess(message, param);
                                    }
                                }
                            } catch (Exception e) {
                                if (callBack != null) {
                                    callBack.onError(e);
                                    ToastUtil.showToast(context,e.getMessage() + "");//"数据异常"
                                }

                            }
                            if (callBack != null) {
                                callBack.onFinish();
                            }
                            return;
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return;
    }




}
