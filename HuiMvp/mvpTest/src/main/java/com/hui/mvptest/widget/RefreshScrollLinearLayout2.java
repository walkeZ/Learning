package com.hui.mvptest.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Scroller;

import com.hui.mvptest.R;
import com.hui.mvptest.util.ViewUtil;

/**
 * Created by walke.Z on 2017/8/14.
 */

public class RefreshScrollLinearLayout2 extends LinearLayout {
    private static final String TAG = "RefreshScrollLinearLayout2";
    private float scrollSpead=2.5f;

    private Scroller scroller;
    OnRefreshListener mRefreshListener;

    float downY;
    private int scrollStart;
    private int scrollEnd;
    private int headerHeight;
    public final static int STATE_NORMAL = 0;
    public final static int STATE_READY = 1;
    public final static int STATE_REFRESHING = 2;
    private int mState = STATE_NORMAL;
    private View headerView;
    private VelocityTracker velocityTracker;


    public RefreshScrollLinearLayout2(Context context) {
        this(context,null);
    }

    public RefreshScrollLinearLayout2(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RefreshScrollLinearLayout2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        scroller = new Scroller(context);
        headerView = View.inflate(context, R.layout.refresh_header, null);
        this.addView(headerView,0);
        headerHeight = ViewUtil.getViewHeight(headerView);
        View childAt = getChildAt(1);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public boolean isFocused() {
        return true;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
       // onTouchEvent(ev);
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        if (velocityTracker == null) {
            velocityTracker = VelocityTracker.obtain();
        }
        velocityTracker.addMovement(ev);

        float eventY = ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                scrollStart = getScrollY();
                downY = eventY;
                Log.i(TAG, "onInterceptTouchEvent: ------------->downY="+downY);
                break;

            case MotionEvent.ACTION_MOVE:
                float v = eventY - downY;
                Log.i(TAG, "onInterceptTouchEvent: ------------->v="+v);
                if (v>0&& MyWebView.isTop) {//下拉
                    //scrollTo(0, (int) ((int) scrollStart - (eventY - downY)));
                    scrollTo(0, (int) ((int) scrollStart - (eventY - downY)/scrollSpead));
                }else {

                }

                break;

            case MotionEvent.ACTION_UP:

                velocityTracker.computeCurrentVelocity(1000);
                float yVelocity = velocityTracker.getYVelocity();

                scrollEnd = getScrollY();
                scroller.startScroll(0, scrollEnd, 0, -scrollEnd);
                postInvalidate();
                Log.i(TAG, "onInterceptTouchEvent: ACTION_UP------------> y = "+eventY+" -----> yVelocity = "+yVelocity);
                break;
        }

        return super.onInterceptTouchEvent(ev);
    }

    public void setState(int state) {
        if (state == mState) return ;

        if (state == STATE_REFRESHING) {	// 显示进度
           headerView.setVisibility(VISIBLE);
        } else {	// 显示箭头图片

        }

        switch(state){
            case STATE_NORMAL:

                break;
            case STATE_READY:

                break;
            case STATE_REFRESHING:
               headerView.setVisibility(VISIBLE);
                break;
            default:
        }

        mState = state;
    }


    @Override
    public void computeScroll() {
        super.computeScroll();

        if (scroller.computeScrollOffset()) {
            scrollTo(0, scroller.getCurrY());
            postInvalidate();
        }
    }

    public void setRefreshListener(OnRefreshListener refreshListener) {
        mRefreshListener = refreshListener;
    }

    public interface OnRefreshListener {
        void onPageChange(int currentPage);
    }
}
