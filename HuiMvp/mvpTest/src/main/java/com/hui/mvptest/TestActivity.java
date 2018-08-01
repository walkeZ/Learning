package com.hui.mvptest;

import android.graphics.Color;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.hui.mvptest.base.activity.ButterKnifeActivity;

import butterknife.BindView;

/**
 * Created by walke.Z on 2017/8/14.
 */

public class TestActivity extends ButterKnifeActivity {
    @BindView(R.id.at_webView)
    WebView mWebView;

    @Override
    public int layoutId() {
        return R.layout.activity_test;
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

    }
    public void click(View v) {
        toast("TestActivity");
    }

    public void toasting(View v) {
        toast("toasting");
    }


}
