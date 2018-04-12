package com.gdcaihui.luckycoin.android.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * @author View
 * @date 2016/11/29 11:55
 */
public class GViewPager extends NoPreloadViewPager {
    public GViewPager(Context context) {
        super(context);
    }

    public GViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        return false;
    }
}
