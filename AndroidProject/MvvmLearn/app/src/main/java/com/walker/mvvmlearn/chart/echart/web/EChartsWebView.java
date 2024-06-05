package com.walker.mvvmlearn.chart.echart.web;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class EChartsWebView extends WebView {
    private boolean requestDisallowInterceptTouchEvent = true;
    private static int emptyFontSize = 14;
    private static String emptyMsg = "暂无数据~";

    public EChartsWebView(@NonNull Context context) {
        this(context, null);
    }

    public EChartsWebView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EChartsWebView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @SuppressLint({"ClickableViewAccessibility", "SetJavaScriptEnabled"})
    private void init() {
        this.getSettings().setJavaScriptEnabled(true);
        this.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        this.getSettings().setSupportZoom(false);
        this.getSettings().setDisplayZoomControls(false);

        this.getSettings().setAllowFileAccess(true);
        this.getSettings().setAllowContentAccess(true);

        this.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        requestDisallowInterceptTouchEvent(requestDisallowInterceptTouchEvent);
        return super.onTouchEvent(event);
    }

    public void setData(String data) {
        loadData(data);
    }

    public void setEmpty() {
        loadData("");
    }

    public void setEmpty(String msg, int emptyFontSize) {
        emptyMsg = msg;
        this.emptyFontSize = emptyFontSize;
        loadData("");
    }

    private void loadData(String data) {
        this.loadUrl("file:///android_asset/echart/app_echart.html");
        this.setWebViewClient(new EChartsWebViewClient(data));
    }

    public static class EChartsWebViewClient extends WebViewClient {

        private String data;

        public EChartsWebViewClient(String data) {
            this.data = data;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(final WebView view, String url) {
            view.post(() -> {
                if (TextUtils.isEmpty(data)) {
                    String emptyUrl = String.format("javascript:setEmpty(%s, %s)", "'" + emptyMsg + "'", emptyFontSize);
                    view.loadUrl(emptyUrl);
                } else
                    view.loadUrl(String.format("javascript:setData(%s)", data));
            });
            super.onPageFinished(view, url);
        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
        }
    }
}

