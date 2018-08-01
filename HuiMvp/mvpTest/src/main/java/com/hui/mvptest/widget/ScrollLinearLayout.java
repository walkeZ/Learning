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

/**
 * Created by walke.Z on 2017/8/14.
 */

public class ScrollLinearLayout extends LinearLayout {
    private static final String TAG = "VerticalPager";
    private float scrollSpead=2.5f;
    private Scroller scroller;
    float downY;
    OnRefreshListener mRefreshListener;
    private int childCount;
    private int screenHeight;
    private int scrollStart;
    private int scrollEnd;
    private int currentPage;
    private VelocityTracker velocityTracker;
    private int previousPage;

    public ScrollLinearLayout(Context context) {
        this(context,null);
    }

    public ScrollLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ScrollLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        screenHeight = getResources().getDisplayMetrics().heightPixels;
        scroller = new Scroller(context);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (changed) {
            for (int i = 0; i < childCount; i++) {
                View childView = getChildAt(i);
                childView.layout(l, i * screenHeight, r, (i + 1) * screenHeight);

                LayoutParams layoutParams = (LayoutParams) childView.getLayoutParams();
                layoutParams.height = screenHeight;
                childView.setLayoutParams(layoutParams);
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            int heightSpec = MeasureSpec.makeMeasureSpec(screenHeight, MeasureSpec.EXACTLY);
            measureChild(childView, widthMeasureSpec, heightSpec);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float eventY = event.getY();

        if (velocityTracker == null) {
            velocityTracker = VelocityTracker.obtain();
        }
        velocityTracker.addMovement(event);

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
                previousPage = currentPage;
                scrollEnd = getScrollY();

                //滚动至特定页码
                int dy = scrollEnd - scrollStart;
                Log.e(TAG, "dy=" + dy);

                velocityTracker.computeCurrentVelocity(1000);
                float yVelocity = velocityTracker.getYVelocity();
                Log.e(TAG, "yVelocity=" + yVelocity);

                if (dy >= screenHeight / 2 || yVelocity < -600) {
                    //滚动至下一页
                    Log.e(TAG, "currentPage ++");
                    currentPage++;
                } else if (dy <= -(screenHeight / 2) || yVelocity > 600) {
                    //滚动至上一页
                    Log.e(TAG, "currentPage --");
                    currentPage--;
                }

                if (currentPage > childCount - 1) {
                    currentPage = childCount - 1;
                } else if (currentPage < 0) {
                    currentPage = 0;
                }
                currentPage=0;

                scroller.startScroll(0, scrollEnd, 0, screenHeight * currentPage - scrollEnd);
                postInvalidate();

                if (velocityTracker != null) {
                    velocityTracker.recycle();
                    velocityTracker = null;
                }

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
