package com.gdcaihui.luckycoin.android.business.web;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ProgressBar;

import com.gdcaihui.luckycoin.android.base.ActionBarActivity;
import com.gdcaihui.luckycoin.android.event.LoginEvent;
import com.gdcaihui.luckycoin.android.widget.ActionBarLayout;
import com.gdcaihui.luckycoin.android.widget.ErrorLayout;
import com.itheima.view.BridgeWebView;

/**
 * @author View
 * @date 2016/12/6 14:36
 * <p>
 * 自定义404错误页面！-- http://www.cnblogs.com/winxiang/archive/2012/10/25/2738320.html
 *      几乎完美解决Android的WebView加载失败（404，500），显示的自定义视图
 *      http://blog.csdn.net/qq_20538515/article/details/51064775
 */
public class WebViewActivity extends ActionBarActivity implements ActionBarLayout.ActionBarLayoutClickListener, LoginEvent {

    public static final int ERROR_VIEW = 1;
    public static final int ALI_PAY_FLAG = 2;
    private BridgeWebView mWebView;
    private String packageName;
    private ActionBarLayout mTopLayout;
    private ProgressBar mProgressBar;
    private String mUrl;
    private boolean needClearHistory;
    private ErrorLayout errorLayout;
    private String mLoginCallbackFunction;
    /**
     * handler处理消息机制
     */
    protected Handler handler = new Handler() {
        public void handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    errorLayout.setVisibility(View.GONE);
                    break;
                case 1:
                    errorLayout.setVisibility(View.VISIBLE);
                    break;
                case 2:
                    errorLayout.setVisibility(View.GONE);
                    break;
            }
        }
    };
    private boolean loadError;

    @Override
    protected int abaLayoutId() {
        return 0;
    }

    @Override
    protected void abaInitView(ActionBarLayout titleLayout) {

    }

    @Override
    protected void abaInitData() {

    }


    @Override
    public void onLoginSuccess() {

    }
}
