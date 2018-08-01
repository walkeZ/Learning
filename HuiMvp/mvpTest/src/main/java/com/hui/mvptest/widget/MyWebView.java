package com.hui.mvptest.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;

/**
 * Created by walke.Z on 2017/8/14.
 */

public class MyWebView extends WebView {

    private static final String TAG = "MyWebView";

    public static boolean isTop=true;


    public MyWebView(Context context) {
        super(context);
    }

    public MyWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //l,t代表left,top，也就是触摸点相对左上角的偏移量。而oldl,oldt就是滑动前的偏移量。
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        Log.i("showBug", "-------->l= "+l+"  t= "+t+"  oldl= "+oldl+"  oldt= "+oldt);
        View childAt = this.getChildAt(0);
        isTop=false;
        if (childAt != null && childAt.getMeasuredHeight() <= this.getScrollY() + this.getHeight() ) {

            Log.i("showBug", "-------->onScrollChanged: 到底部了");
        } else if (this.getScrollY() == 0) {
            Log.i("showBug", "-------->onScrollChanged: 顶部");
            isTop=true;
            //getParent().
        }else {

        }
        super.onScrollChanged(l,t,oldl,oldt);
    }

}
