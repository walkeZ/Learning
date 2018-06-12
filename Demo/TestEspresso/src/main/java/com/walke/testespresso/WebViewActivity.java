package com.walke.testespresso;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by walke.Z on 2018/6/12.
 */

public class WebViewActivity extends AppCompatActivity {

    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;
    @BindView(R.id.webView)
    WebView mWebView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        ButterKnife.bind(this);

        WebSettings settings = mWebView.getSettings();
        settings.setDisplayZoomControls(true);
        settings.setJavaScriptEnabled(true);
        settings.setEnableSmoothTransition(true);
        //避免调用系统浏览器
        mWebView.setWebViewClient(new WebViewClient(){

        });

        mWebView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress==100){
//                    mProgressBar.setVisibility(View.GONE);
                }
                mProgressBar.setProgress(newProgress);
            }
        });

        String url = getIntent().getStringExtra("url");
        if (!TextUtils.isEmpty(url)&&url.contains("http"))
            mWebView.loadUrl(url);


    }
}
