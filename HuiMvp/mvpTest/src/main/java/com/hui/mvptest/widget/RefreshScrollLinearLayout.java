package com.hui.mvptest.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Scroller;

import com.hui.mvptest.R;
import com.hui.mvptest.util.ViewUtil;

/**
 * Created by walke.Z on 2017/8/14.
 */

public class RefreshScrollLinearLayout extends LinearLayout {
    private static final String TAG = "VerticalPager";
    private float scrollSpead=2.5f;
    private Scroller scroller;
    float downY;
    OnRefreshListener mRefreshListener;
    private int contentHeight;
    private int scrollStart;
    private int scrollEnd;
    private int headerHeight;

    public RefreshScrollLinearLayout(Context context) {
        this(context,null);
    }

    public RefreshScrollLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RefreshScrollLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        int screenHeight = getResources().getDisplayMetrics().heightPixels;
        scroller = new Scroller(context);
        View inflate = View.inflate(context, R.layout.refresh_header, null);
        this.addView(inflate,0);
        headerHeight = ViewUtil.getViewHeight(inflate);
        contentHeight=screenHeight-headerHeight;
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
    public boolean onTouchEvent(MotionEvent event) {
        float eventY = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                scrollStart = getScrollY();
                downY = eventY;
                break;

            case MotionEvent.ACTION_MOVE:
                //scrollTo(0, (int) ((int) scrollStart - (eventY - downY)));
                scrollTo(0, (int) ((int) scrollStart - (eventY - downY)/scrollSpead));
                break;

            case MotionEvent.ACTION_UP:
                scrollEnd = getScrollY();
                scroller.startScroll(0, scrollEnd, 0, -scrollEnd);
                postInvalidate();

                break;
        }

        return true;
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
