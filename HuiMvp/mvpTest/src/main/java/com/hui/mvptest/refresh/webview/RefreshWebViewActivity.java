package com.hui.mvptest.refresh.webview;

import android.graphics.Color;
import android.os.Handler;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.hui.mvptest.R;
import com.hui.mvptest.base.activity.ButterKnifeActivity;

import butterknife.BindView;

/**
 * Created by walke.Z on 2017/8/14.
 */

public class RefreshWebViewActivity extends ButterKnifeActivity {
    @BindView(R.id.at_webView)
    WebView mWebView;
    @BindView(R.id.arw_RefreshWebViewLinearLayout)
    RefreshWebViewLinearLayout mRefreshWebViewLinearLayout;

    @Override
    public int layoutId() {
        return R.layout.activity_refresh_webview;
    }

    @Override
    protected void initData() {
        initWebView();
    }

    private void initWebView() {
        //设置编码
        mWebView.getSettings().setDefaultTextEncodingName("utf-8");
        //支持js
        mWebView.getSettings().setJavaScriptEnabled(true);
        //设置背景颜色 透明
        mWebView.setBackgroundColor(Color.argb(0, 0, 0, 0));
        //防止跳转到系统自带的浏览器中打开
        mWebView.setWebViewClient(new WebViewClient());

        //载入js
        mWebView.loadUrl("http://www.baidu.com/");
        mRefreshWebViewLinearLayout.setRefreshListener(new RefreshWebViewLinearLayout.OnRefreshListener() {
            @Override
            public void onRefreshing() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mRefreshWebViewLinearLayout.refreshComplete();
                    }
                },2000);
            }
        });

    }

    public void click(View v) {
        toast("TestActivity");
    }

    public void toasting(View v) {
        toast("toasting");
    }

}
