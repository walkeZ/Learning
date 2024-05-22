package com.walker.mvvmlearn.net.retrofit2;

import android.util.Log;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * @author walker
 * @date 2024/5/21
 * @description 参数拦截器
 * 这个拦截器主要做了2个工作：
 * * 1. 添加校验参数 params 用于校验
 * * 2. 添加通用参数 减少各个接口工作量
 */
public class ParamInterceptor implements Interceptor {
    private static String TAG = "ParamInterceptor";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request oldRequest = chain.request();
        Map<String, String> params = parseParams(oldRequest);
        if (params == null) {
            params = new HashMap<>();
        }
        params.putAll(getCommentParams());
        if (params.containsKey(NetConfig.SPECIAL_PARAM)) {//是否有指定客户端的特殊字段,备注：需要仔细检查
            params.put("xxx", "0"); // 给特殊字段二次操作
            return null;
        }

        // https://blog.csdn.net/jdsjlzx/article/details/52063950
        // 添加新的参数
        HttpUrl.Builder urlBuilder = oldRequest.url()
                .newBuilder()
                .scheme(oldRequest.url().scheme())
                .host(oldRequest.url().host());
        // 公共参数
//                .addQueryParameter("param0", "value0")
//                .addQueryParameter("param1", "value1")
//                .addQueryParameter("param2", "value2");

        // 新的请求
        Request newRequest = oldRequest.newBuilder()
//                .addHeader() // header 头部参数可以按需提交
//                .addHeader()
                .method(oldRequest.method(), oldRequest.body())
                .url(urlBuilder.build())
                .build();

        return chain.proceed(newRequest);
    }

    /**
     * 提供参数
     *
     * @param request
     * @return
     */
    private static Map<String, String> parseParams(Request request) {
        String method = request.method();
        Map<String, String> params = null;
        if ("GET".equals(method)) {
            params = doGet(request);
            Log.w(TAG, "url " + request.url() + ", get Params: " + params);
        } else {
            RequestBody requestBody = request.body();
            if (requestBody != null && requestBody instanceof FormBody) {
                params = doForm(request);
                Log.w(TAG, "url " + request.url() + ",post Params: " + params);
            }
        }
        return params;
    }

    /**
     * 获取get方式的请求参数
     *
     * @param request
     * @return
     */
    private static Map<String, String> doGet(Request request) {
        Map<String, String> map = null;
        okhttp3.HttpUrl httpUrl = request.url();
        Set<String> strings = httpUrl.queryParameterNames();
        if (strings != null) {
            Iterator<String> iterator = strings.iterator();
            map = new HashMap<>();
            int i = 0;
            while (iterator.hasNext()) {
                String name = iterator.next();
                String value = httpUrl.queryParameterValue(i);
                map.put(name, value);
                i++;
            }
        }
        return map;
    }

    /**
     * 获取表单的请求参数
     *
     * @param request
     * @return
     */
    private static Map<String, String> doForm(Request request) {
        Map<String, String> map = null;
        FormBody formBody = null;
        try {
            formBody = (FormBody) request.body();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (formBody != null) {
            int size = formBody.size();
            if (size > 0) {
                map = new HashMap<>();
                for (int i = 0; i < size; i++) {
                    map.put(formBody.name(i), formBody.value(i));
                }
            }
        }
        return map;
    }

    /**
     * 获取通用参数
     *
     * @return
     */
    private Map<String, String> getCommentParams() {
//        String deviceModel = MethodCommon.getDevice();
//        String deviceUniqueId = MethodCommon.getDeviceId(TyjApp.getInstance());
//        String appVersion = MethodCommon.getVersionName(TyjApp.getInstance());
//
//        Map<String, String> paramsMap = new HashMap<>();
//        paramsMap.put("appId", UrlConstants.APPID);
//        //游客判断token是否为空
//        if (TextUtils.isEmpty(LoginEntry.Instance().getToken())) {
//            LoginEntry.Instance().setToken(AppUtil.getUUId());
//        }
//        paramsMap.put("token", LoginEntry.Instance().getToken());
//        paramsMap.put("version", "1");
//        paramsMap.put("deviceModel", deviceModel);
//        paramsMap.put("deviceUniqueId", deviceUniqueId);
//        paramsMap.put("appVersion", appVersion);
//        paramsMap.put("clientType", "android");
//        paramsMap.put("User-Agent", MethodCommon.getUserAgent(TyjApp.getInstance()));
//        return paramsMap;
        return new HashMap<>();
    }
}