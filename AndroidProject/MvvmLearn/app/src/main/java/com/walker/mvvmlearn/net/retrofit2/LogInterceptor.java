package com.walker.mvvmlearn.net.retrofit2;

import android.util.Log;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.URLDecoder;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Author: Ly
 * Data：2018/9/5-11:22
 * Description:日志拦截器
 */
public class LogInterceptor implements Interceptor {
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Response response = null;
        String requestUrl = "";
        try {
            Request request = chain.request();
            requestUrl = request.url().toString();
            logRequest(request);
            response = chain.proceed(request);
        } catch (Exception exc) {
            exc.printStackTrace(); // 2020/6/15 16:17:51 + 发现没有400 的报错返回
            Log.e(LogInterceptor.class.getSimpleName(), "intercept().exc:" + exc.getMessage() + "\n  请求的url:" + requestUrl);
            return response;
        }
        return logResponse(response);
//        logResponse(response); // response.newBuilder().build() copy 一份response给log
//        return response.newBuilder().build(); // response.body().string()之后，response中的流会被关闭，程序会报错，我们需要创建出一个新的response给应用层处理
    }

    /**
     * 打印Request
     *
     * @param request
     */
    private void logRequest(Request request) {
        try {
            String url = request.url().toString();
            Headers headers = request.headers();
            String method = request.method();

            StringBuilder params = new StringBuilder();
            if (headers != null && headers.size() > 0) {
                params.append("headers:");
                for (int i = 0; i < headers.size(); i++) {
                    params.append(headers.name(i) + " - " + headers.value(i));
                }
            }
            RequestBody requestBody = request.body();
            if (requestBody != null) {
                MediaType mediaType = requestBody.contentType();
                if (mediaType != null) {
                    if (isText(mediaType)) {
                        String resp = requestBody.toString();
                        params.append(",rqsBody " + resp);
                    } else {
                        log("不是text");
                        params.append(",rqsBody 不是text");
                    }
                }
            }
            log("req mt " + method + ", url " + url + ", " + params);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 打印Response
     *
     * @param response
     */
    private Response logResponse(Response response) {
        try {
            Response.Builder builder = response.newBuilder();
            Response clone = builder.build();

            ResponseBody body = clone.body();
//            body.toString()、string() 是不同的
//            Log.i(LogInterceptor.class.getSimpleName(), "logResponse: " + body.string());
            if (body != null) {
                MediaType mediaType = body.contentType();
                if (mediaType != null) {
                    if (isText(mediaType)) {
                        String requestUrl = clone.request().url().toString();
                        RequestBody requestBody = clone.request().body();
                        if (requestBody instanceof FormBody) {
                            StringBuilder sb = new StringBuilder();
                            for (int i = 0; i < ((FormBody) requestBody).size(); i++) {
                                String pas = ((FormBody) requestBody).encodedValue(i);
                                sb.append(((FormBody) requestBody).encodedName(i) + " - " + URLDecoder.decode(pas, "utf-8") + "\n");
                            }
                            // 返回时log请求参数
                            log("rsp url : " + requestUrl + "\n req body = " + sb);
                        }

                        /**
                         * 因为response.body().string()之后，response中的流会被关闭，程序会报错，我们需要创建出一个新的response给应用层处理
                         */
                        // 方式①
//                        String resp = body.string();
//                        log("rsp url: " + URLDecoder.decode(requestUrl, "utf-8") + "\n resp: " + resp);
//                        body = ResponseBody.create(mediaType, resp); // 这个不能少
//                        return response.newBuilder().body(body).build();
                        // 方式②
                        ResponseBody responseBody = response.peekBody(1024 * 1024);
                        log("rsp url: " + URLDecoder.decode(requestUrl, "utf-8") + "\n resp: " + responseBody.string());
                        return response;
                    } else {
                        log("不是text");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(LogInterceptor.class.getSimpleName(), "intercept().exc:" + e.getMessage() + "\n 请求的url:" + response.request().url());
        }
        return response;
    }

    private boolean isText(MediaType mediaType) {
        return mediaType != null && ("text".equals(mediaType.subtype()) || "json".equals(mediaType.subtype()) || "xml".equals(mediaType.subtype()) || "html".equals(mediaType.subtype()) || "webviewhtml".equals(mediaType.subtype()) || "x-www-form-urlencoded".equals(mediaType.subtype()));
    }

    private void log(String msg) {
        Log.i(LogInterceptor.class.getSimpleName(), msg);
    }
}
